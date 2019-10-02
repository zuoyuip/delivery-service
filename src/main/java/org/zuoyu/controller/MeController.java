package org.zuoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zuoyu.model.Delivery;
import org.zuoyu.model.Suggest;
import org.zuoyu.model.UserInfo;
import org.zuoyu.service.ICriteriaService;
import org.zuoyu.service.IDeliveryService;
import org.zuoyu.service.ISuggestService;
import org.zuoyu.util.Result;

/**
 * 个人包裹.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 09:36
 **/
@Slf4j
@Api(value = "“我的”有关包裹信息操作API", tags = "meAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MeController {

  private final IDeliveryService iDeliveryService;
  private final ISuggestService iSuggestService;
  private final ICriteriaService iCriteriaService;

  public MeController(IDeliveryService iDeliveryService, ISuggestService iSuggestService,
      ICriteriaService iCriteriaService) {
    this.iDeliveryService = iDeliveryService;
    this.iSuggestService = iSuggestService;
    this.iCriteriaService = iCriteriaService;
  }

  @ApiOperation(value = "根据当前用户的唯一标识获取其发布的所有包裹信息", notes = "注意：若返回状态码为204,表示没有该包裹信息；若返回状态码为500,表示服务器异常",
      response = Delivery.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "deliveryUserId", value = "当前用户的唯一标识", required = true, dataTypeClass = String.class)
  @PreAuthorize("authenticated")
  @GetMapping("/deliveryUser/{deliveryUserId}")
  public ResponseEntity<List<Delivery>> listMeDelivery(@PathVariable String deliveryUserId) {
    List<Delivery> deliveries = iDeliveryService.deliveriesMe(deliveryUserId);
    if (deliveries.size() < 1) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(deliveries);
  }

  @ApiOperation(value = "根据当前用户的唯一标识获取其接收的所有包裹信息", notes = "注意：若返回状态码为204,表示没有该包裹信息；若返回状态码为500,表示服务器异常",
      response = Delivery.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "deliveryDeliveryUserId", value = "当前用户的唯一标识", required = true, dataTypeClass = String.class)
  @PreAuthorize("authenticated")
  @GetMapping("/deliveryWorker/{deliveryDeliveryUserId}")
  public ResponseEntity<List<Delivery>> listMeDeliveriesDelivery(
      @PathVariable String deliveryDeliveryUserId) {
    List<Delivery> deliveries = iDeliveryService.deliveriesDeliveryMe(deliveryDeliveryUserId);
    if (deliveries.size() < 1) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(deliveries);
  }

  @ApiOperation(value = "建议反馈", notes = "注意：若返回状态码为500，表示服务器异常导致的反馈失败", response = Result.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParam(name = "Suggest.Class", value = "反馈信息实例", required = true, dataTypeClass = Suggest.class)
  @PostMapping("/suggest")
  public ResponseEntity<Result> addSuggest(Suggest suggest) {
    String content = suggest.getSuggestContent();
    if (content.trim().isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请填写建议内容"));
    }
    int i = iSuggestService.addSuggest(suggest);
    if (i < 1) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Result.message("反馈失败，服务器异常"));
    }
    return ResponseEntity.ok(Result.message("反馈成功"));
  }

  @ApiOperation(value = "审核申请", notes = "注意：若返回状态码为500，表示服务器异常导致的反馈失败", response = Result.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParams(
      {@ApiImplicitParam(name = "userInfo", value = "用户详细信息", required = true, dataTypeClass = UserInfo.class),
          @ApiImplicitParam(name = "multipartFile", value = "证件照片", required = true, dataTypeClass = MultipartFile.class)}
  )
  @PreAuthorize("authenticated")
  @PostMapping("/submitCriteria")
  public ResponseEntity<Result> submitCriteria(UserInfo userInfo,
      @RequestParam("file") MultipartFile multipartFile) {
    if (userInfo == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请填写您的信息"));
    }
    if (multipartFile.isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请上传照片"));
    }
    try {
      int i = iCriteriaService.applicationCriteria(userInfo, multipartFile);
      if (i < 1) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Result.message("服务器内部错误，请联系管理员"));
      }
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Result.message("照片解析错误"));
    }
    return ResponseEntity.ok(Result.message("审核申请成功"));
  }
}
