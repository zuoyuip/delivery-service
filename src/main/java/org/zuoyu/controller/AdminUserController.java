package org.zuoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.model.User;
import org.zuoyu.service.IUserService;
import org.zuoyu.util.Result;

/**
 * 管理员对账户的操作.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-18 22:11
 **/
@Slf4j
@Api(value = "管理员账户信息操作API", tags = "AdminUserAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/admin/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminUserController {

  private final IUserService iUserService;

  public AdminUserController(IUserService iUserService) {
    this.iUserService = iUserService;
  }

  @ApiOperation(value = "获取所有安全账户", notes = "该方法不可轻易调用", response = User.class, ignoreJsonView = true)
  @GetMapping(path = "/all")
  public ResponseEntity<List<User>> selectAll() {
    List<User> users = iUserService.listUser();
    return ResponseEntity.ok(users);
  }

  @ApiOperation(value = "根据唯一标识获取对应的账户信息", notes = "注意：若返回状态码为204,表示没有该账户信息；若返回状态码为500,表示服务器异常",
      response = User.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "userId", value = "账户信息实例的唯一标识", required = true, dataTypeClass = String.class)
  @GetMapping(path = "/{userId}")
  public ResponseEntity<User> selectUserById(@PathVariable String userId) {
    User user = iUserService.getUserById(userId);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(user);
  }

  @ApiOperation(value = "根据唯一标识禁用该用户", notes = "注意：若返回状态码为500,表示服务器异常",
      response = Result.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "userId", value = "账户信息实例的唯一标识", required = true, dataTypeClass = String.class)
  @PutMapping(path = "/prohibit/{userId}")
  public ResponseEntity<Result> prohibitUser(@PathVariable String userId){
    int i = iUserService.prohibitUser(userId);
    if (i < 1){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.message("禁用失败"));
    }
    return ResponseEntity.ok(Result.message("禁用成功"));
  }
}
