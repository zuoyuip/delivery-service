package org.zuoyu.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zuoyu.service.IDeliveryService;
import org.zuoyu.service.IRedisService;

/**
 * 定时任务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-29 14:34
 **/
@Component
public class ScheduledTask {

  private final IDeliveryService iDeliveryService;
  private final IRedisService iRedisService;

  public ScheduledTask(IDeliveryService iDeliveryService,
      IRedisService iRedisService) {
    this.iDeliveryService = iDeliveryService;
    this.iRedisService = iRedisService;
  }

  @Scheduled(cron = "0 0 0 * * ? *")
  public void updateExpressStatus() {
    iRedisService.clearAll();
    iDeliveryService.cancelDeliveries();
  }
}
