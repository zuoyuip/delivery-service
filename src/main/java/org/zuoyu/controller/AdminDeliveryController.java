package org.zuoyu.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
