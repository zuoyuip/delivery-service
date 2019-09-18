package org.zuoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zuoyu.model.Delivery;
import org.zuoyu.service.IDeliveryService;

/**
 * 管理员包裹信息操作.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-18 17:37
 **/
@Slf4j
@Api(value = "管理员包裹信息操作API", tags = "AdminDeliveryAPI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/admin/delivery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminDeliveryController {

  private final IDeliveryService iDeliveryService;

  public AdminDeliveryController(
      IDeliveryService iDeliveryService) {
    this.iDeliveryService = iDeliveryService;
  }


  @ApiOperation(value = "获取所有的包裹信息",
      notes = "注意：若返回状态码为204，表示没有信息", response = Delivery.class, ignoreJsonView = true)
  @GetMapping(path = "/all")
  public ResponseEntity<List<Delivery>> selectDeliveryAll() {
    List<Delivery> deliveries = iDeliveryService.selectDeliveryAll();
    if (deliveries.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(deliveries);
  }


  @ApiOperation(value = "获取所有未被领取的包裹信息",
      notes = "注意：若返回状态码为204，表示没有信息", response = Delivery.class, ignoreJsonView = true)
  @GetMapping(path = "/notReceive")
  public ResponseEntity<List<Delivery>> selectDeliveryNotReceive() {
    List<Delivery> deliveries = iDeliveryService.selectDeliveryAll();
    if (deliveries.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(deliveries);
  }


  @ApiOperation(value = "获取所有已经被领取的包裹信息",
      notes = "注意：若返回状态码为204，表示没有信息", response = Delivery.class, ignoreJsonView = true)
  @GetMapping(path = "/receive")
  public ResponseEntity<List<Delivery>> selectDeliveryReceive() {
    List<Delivery> deliveries = iDeliveryService.selectDeliveryAll();
    if (deliveries.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(deliveries);
  }

}
