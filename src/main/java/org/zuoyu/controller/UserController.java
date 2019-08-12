package org.zuoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.model.User;
import org.zuoyu.service.IUserService;
import org.zuoyu.util.Result;

/**
 * 安全用户.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-11 12:23
 **/
@Api(value = "安全账户操作API", tags = "userAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

  private final IUserService iUserService;

  public UserController(IUserService iUserService) {
    this.iUserService = iUserService;
  }

  /**
   * 注册安全账户
   *
   * @param user - 安全账户
   * @return - 响应信息
   */
  @ApiOperation(value = "注册账户", notes = "根据传入的安全用户实例信息进行注册", response = ResponseEntity.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParam(name = "user", value = "安全用户实例", required = true, dataTypeClass = User.class)
  @PostMapping
  public ResponseEntity<Result> register(User user) {
    boolean isPresence = iUserService.isPresenceByUserPhone(user.getUserPhone());
    if (isPresence){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.message("账户已存在！"));
    }
    int i = iUserService.insertUser(user);
    if (i < 1) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.message("注册失败！"));
    }
    return ResponseEntity.ok(Result.message("注册成功！"));
  }
}
