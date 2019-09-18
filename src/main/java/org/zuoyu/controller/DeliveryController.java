package org.zuoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.model.Delivery;
import org.zuoyu.service.IDeliveryService;
import org.zuoyu.util.Result;

/**
 * 包裹信息.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-15 17:53
 **/
@Slf4j
@Api(value = "包裹信息操作API", tags = "deliveryAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/delivery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeliveryController {

  private final IDeliveryService iDeliveryService;

  public DeliveryController(IDeliveryService iDeliveryService) {
    this.iDeliveryService = iDeliveryService;
  }

  @ApiOperation(value = "获取未被接单的包裹信息(只有简介信息，涉及重要私密信息不显示)",
      notes = "注意：若返回状态码为204，表示没有信息", response = Delivery.class, ignoreJsonView = true)
  @GetMapping
  public ResponseEntity<List<Delivery>> selectAll() {
    List<Delivery> deliveries = iDeliveryService.listDelivery();
    if (deliveries.size() < 1) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(deliveries);
  }


  @ApiOperation(value = "添加包裹信息", notes = "注意：若返回状态码为500，表示服务器异常导致的添加失败", response = Result.class,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, ignoreJsonView = true)
  @ApiImplicitParam(name = "Delivery.Class", value = "包裹信息实例", required = true, dataTypeClass = Delivery.class)
  @PostMapping
  @PreAuthorize("authenticated")
  public ResponseEntity<Result> addDelivery(Delivery delivery) {
    if (delivery == null){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Result.message("请填写快递信息"));
    }
    int i = iDeliveryService.insertDelivery(delivery);
    if (i < 1) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.message("添加失败"));
    }
    return ResponseEntity.ok(Result.message("添加成功"));
  }

  @ApiOperation(value = "根据唯一标识获取对应的包裹信息", notes = "注意：若返回状态码为204,表示没有该包裹信息；若返回状态码为500,表示服务器异常",
      response = Delivery.class, ignoreJsonView = true)
  @ApiImplicitParam(name = "deliveryId", value = "包裹信息实例的唯一标识", required = true, dataTypeClass = String.class)
  @GetMapping(path = "/{deliveryId}")
  public ResponseEntity<Delivery> getDeliveryById(@PathVariable String deliveryId) {
    Delivery delivery = iDeliveryService.getDeliveryById(deliveryId);
    if (delivery == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    if (delivery.getDeliveryStatus()) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.ok(delivery);
  }
}
