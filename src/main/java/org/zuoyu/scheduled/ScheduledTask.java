package org.zuoyu.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zuoyu.service.IDeliveryService;

/**
 * 定时任务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-29 14:34
 **/
@Component
@Slf4j
public class ScheduledTask {

  private final IDeliveryService iDeliveryService;

  public ScheduledTask(IDeliveryService iDeliveryService) {
    this.iDeliveryService = iDeliveryService;
  }

  @Scheduled(cron = "0 0 0 * * ?")
  public void updateExpressStatus() {
    log.info("------执行------clearAllDeliveries------");
    iDeliveryService.clearAllDeliveries();
  }
}
