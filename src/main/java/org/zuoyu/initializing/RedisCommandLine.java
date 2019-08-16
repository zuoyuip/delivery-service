package org.zuoyu.initializing;

import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.zuoyu.service.IDeliveryService;

/**
 * 初始化Redis的缓存.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-16 17:58
 **/
@Component
public class RedisCommandLine implements CommandLineRunner {

  private final RedisTemplate<String, Object> redisTemplate;

  private final IDeliveryService iDeliveryService;

  public RedisCommandLine(RedisTemplate<String, Object> redisTemplate,
      IDeliveryService iDeliveryService) {
    this.redisTemplate = redisTemplate;
    this.iDeliveryService = iDeliveryService;
  }

  @Override
  public void run(String... args) {
    Set<String> keys = redisTemplate.keys("*");
    if (keys == null || keys.size() < 1) {
      iDeliveryService.listDelivery();
      return;
    }
    redisTemplate.delete(keys);
    iDeliveryService.listDelivery();
  }
}
