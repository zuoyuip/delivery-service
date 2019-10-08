package org.zuoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.model.User;
import org.zuoyu.model.UserInfo;
import org.zuoyu.service.ICriteriaService;
import org.zuoyu.service.IUserService;
import org.zuoyu.service.IVerificationCodeService;
import org.zuoyu.util.Result;
import org.zuoyu.util.UserUtil;

/**
 * 安全用户.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-11 12:23
 **/
@Slf4j
@Api(value = "安全账户操作API", tags = "userAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

  private final IUserService iUserService;
  private final ICriteriaService iCriteriaService;
  private final IVerificationCodeService iVerificationCodeService;

  public UserController(IUserService iUserService, ICriteriaService iCriteriaService,
      IVerificationCodeService iVerificationCodeService) {
    this.iUserService = iUserService;
    this.iCriteriaService = iCriteriaService;
    this.iVerificationCodeService = iVerificationCodeService;
  }

  @ApiOperation(value = "根据传入的安全用户实例信息进行注册", notes = "注意：返回500表示服务器异常导致注册失败", response = Result.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "user", value = "安全用户实例", required = true, dataTypeClass = User.class),
      @ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataTypeClass = String.class)
  })
  @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<Result> register(User user, @RequestParam("verifyCode") String verifyCode) {
    if (paramIsNull(verifyCode)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请输入验证码"));
    }
    if (user == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请填写注册信息"));
    }
    boolean isPresence = iUserService.isPresenceByUserPhone(user.getUserPhone());
    if (isPresence) {
      return ResponseEntity.status(HttpStatus.CREATED).body(Result.message("账户已存在！"));
    }
    int i = iUserService.insertUser(user);
    if (i < 1) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.message("注册失败！"));
    }
    return ResponseEntity.ok(Result.message("注册成功！"));
  }

  @ApiOperation(value = "获取所有安全账户", notes = "该方法不可轻易调用", response = User.class, ignoreJsonView = true)
  @GetMapping
  public ResponseEntity<List<User>> selectAll() {
    List<User> users = iUserService.listUser();
    return ResponseEntity.ok(users);
  }

  @ApiOperation(value = "获取当前的安全用户", notes = "该方法仅适用客户端")
  @PreAuthorize("authenticated")
  @GetMapping(path = "/authentication")
  public ResponseEntity<Authentication> getCurrentUser(Authentication authentication) {
    return ResponseEntity.ok(authentication);
  }


  @ApiOperation(value = "修改密码", notes = "注意：若返回状态码为500，表示服务器异常导致的反馈失败", response = Result.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParams(
      {@ApiImplicitParam(name = "userPhone", value = "账户的手机号", required = true, dataTypeClass = String.class),
          @ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataTypeClass = String.class),
          @ApiImplicitParam(name = "passWord", value = "密码", required = true, dataTypeClass = String.class)}
  )
  @PostMapping(path = "/forgetUser")
  public ResponseEntity<Result> forgetUser(@RequestParam("userPhone") String userPhone,
      @RequestParam("verifyCode") String verifyCode,
      @RequestParam("passWord") String passWord) {
    if (paramIsNull(userPhone)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请输入手机号码"));
    }
    if (paramIsNull(verifyCode)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请输入验证码"));
    }
    if (paramIsNull(passWord)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请输入密码"));
    }
    if (!iUserService.isPresenceByUserPhone(userPhone)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("该账户不存在"));
    }
    User user = iUserService.getUserDetailsByUserPhone(userPhone);
    user.setUserPassword(passWord);
    int i = iUserService.updateUserById(user, true);
    if (i < 1) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Result.message("密码修改失败，请联系管理员"));
    }
    return ResponseEntity.ok(Result.message("密码修改成功"));
  }


  @ApiOperation(value = "修改手机号", notes = "注意：若返回状态码为500，表示服务器异常导致的反馈失败", response = Result.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParams(
      {@ApiImplicitParam(name = "newUserPhone", value = "要更改为的手机号", required = true, dataTypeClass = String.class),
          @ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataTypeClass = String.class),
          @ApiImplicitParam(name = "passWord", value = "原密码", required = true, dataTypeClass = String.class)}
  )
  @PreAuthorize("authenticated")
  @PostMapping(path = "/modifyUser")
  public ResponseEntity<Result> modifyUser(@RequestParam("newUserPhone") String newUserPhone,
      @RequestParam("verifyCode") String verifyCode,
      @RequestParam("passWord") String passWord) {
    if (paramIsNull(newUserPhone)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请输入手机号码"));
    }
    if (paramIsNull(verifyCode)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请输入验证码"));
    }
    if (paramIsNull(passWord)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请输入原密码"));
    }
    if (!iUserService.verifyUser(passWord)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("原密码错误"));
    }
    if (iUserService.isPresenceByUserPhone(newUserPhone)) {
      return ResponseEntity.status(HttpStatus.CREATED).body(Result.message("该手机号已存在！"));
    }
    User user = UserUtil.currentUser();
    user.setUserPhone(newUserPhone);
    int i = iUserService.updateUserById(user, false);
    if (i < 1) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Result.message("手机号修改失败，请联系管理员"));
    }
    return ResponseEntity.ok(Result.message("手机号修改成功，请重新登录"));
  }

  @ApiOperation(value = "根据唯一标识获取对应的用户详情信息", notes = "注意：若返回状态码为204,表示没有该用户详情信息；若返回状态码为500,表示服务器异常",
      response = UserInfo.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "userInfoId", value = "用户详情信息实例的唯一标识", required = true, dataTypeClass = String.class)
  @GetMapping(path = "userInfo/{userInfoId}")
  public ResponseEntity<UserInfo> getUserInfoById(@PathVariable String userInfoId) {
    if (paramIsNull(userInfoId)){
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    UserInfo userInfo = iCriteriaService.findUserInfoById(userInfoId);
    if (userInfo == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(userInfo);
  }

  private boolean paramIsNull(String s) {
    return s == null || "".equals(s.trim()) || s.trim().isEmpty();
  }

  @GetMapping("/getVerificationCode")
  public ResponseEntity<Result> getVerificationCode(){
    String verificationCode = iVerificationCodeService.getVerificationCode();
    return ResponseEntity.ok(Result.detail("获取成功!", verificationCode));
  }

  @GetMapping("/clearVerificationCode")
  public ResponseEntity<Result> clearVerificationCode(){
    iVerificationCodeService.clearVerificationCode();
    return ResponseEntity.ok(Result.message("关闭成功!"));
  }

}
