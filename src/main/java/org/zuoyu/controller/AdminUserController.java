package org.zuoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.model.User;
import org.zuoyu.service.IUserService;

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
}
