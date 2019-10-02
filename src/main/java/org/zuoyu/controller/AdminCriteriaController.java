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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.model.Delivery;
import org.zuoyu.model.Review;
import org.zuoyu.model.Suggest;
import org.zuoyu.model.User;
import org.zuoyu.model.UserInfo;
import org.zuoyu.service.ICriteriaService;
import org.zuoyu.service.ISuggestService;
import org.zuoyu.util.Result;

/**
 * 管理员对审核的操作.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-18 21:34
 **/
@Slf4j
@Api(value = "管理员审核操作API", tags = "AdminCriteriaAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/admin/criteria", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminCriteriaController {

  private final ICriteriaService iCriteriaService;
  private final ISuggestService iSuggestService;

  public AdminCriteriaController(
      ICriteriaService iCriteriaService, ISuggestService iSuggestService) {
    this.iCriteriaService = iCriteriaService;
    this.iSuggestService = iSuggestService;
  }

  @ApiOperation(value = "获取所有待审核的用户列表",
      notes = "注意：若返回状态码为204，表示没有信息", response = User.class, ignoreJsonView = true)
  @GetMapping(path = "/wait")
  public ResponseEntity<List<User>> selectWaitCriteria() {
    List<User> users = iCriteriaService.waitCriteria();
    if (users.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(users);
  }


  @ApiOperation(value = "审核申请处理", notes = "注意：若返回状态码为500，表示服务器异常导致的反馈失败", response = Result.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParams(
      {@ApiImplicitParam(name = "userId", value = "账户ID", required = true, dataTypeClass = String.class),
          @ApiImplicitParam(name = "isPass", value = "是否通过申请", required = true, dataTypeClass = boolean.class),
          @ApiImplicitParam(name = "reviewMessage", value = "如果未通过申请，原因说明", required = false, dataTypeClass = String.class)}
  )
  @PutMapping(path = "/deal/{userId}")
  public ResponseEntity<Result> dealWithCriteria(@PathVariable String userId, boolean isPass,
      @RequestParam(required = false) String reviewMessage) {
    int i;
    if (reviewMessage.isEmpty()) {
      i = iCriteriaService.dealWithCriteria(userId, isPass);
    } else {
      i = iCriteriaService.dealWithCriteria(userId, isPass, reviewMessage);
    }
    if (i < 1) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.message("审核操作失败"));
    }
    return ResponseEntity.ok(Result.message("审核操作成功"));
  }

  @ApiOperation(value = "获取所有建议反馈列表",
      notes = "注意：若返回状态码为204，表示没有信息", response = Suggest.class, ignoreJsonView = true)
  @GetMapping(path = "/allSuggest")
  public ResponseEntity<List<Suggest>> selectSuggest() {
    List<Suggest> suggests = iSuggestService.selectAll();
    if (suggests.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(suggests);
  }


  @ApiOperation(value = "根据唯一标识获取对应的审核申请信息", notes = "注意：若返回状态码为204,表示没有该审核申请信息；若返回状态码为500,表示服务器异常",
      response = Review.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "reviewId", value = "审核申请信息实例的唯一标识", required = true, dataTypeClass = String.class)
  @GetMapping(path = "wait/{reviewId}")
  public ResponseEntity<Review> getReviewById(@PathVariable String reviewId) {
    Review review = iCriteriaService.findReviewById(reviewId);
    if (review == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(review);
  }

  @ApiOperation(value = "根据唯一标识获取对应的用户详情信息", notes = "注意：若返回状态码为204,表示没有该用户详情信息；若返回状态码为500,表示服务器异常",
      response = UserInfo.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "userInfoId", value = "用户详情信息实例的唯一标识", required = true, dataTypeClass = String.class)
  @GetMapping(path = "userInfo/{userInfoId}")
  public ResponseEntity<UserInfo> getUserInfoById(@PathVariable String userInfoId) {
    UserInfo userInfo = iCriteriaService.findUserInfoById(userInfoId);
    if (userInfo == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(userInfo);
  }
}
