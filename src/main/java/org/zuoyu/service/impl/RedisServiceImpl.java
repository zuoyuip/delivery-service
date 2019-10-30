package org.zuoyu.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.zuoyu.service.IRedisService;

/**
 * Redis服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-13 14:54
 **/
@Service
class RedisServiceImpl implements IRedisService {

  private final RedisTemplate<String, Object> redisTemplate;

  RedisServiceImpl(
      RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public boolean isExists(String key) {
    Boolean hasKey = redisTemplate.hasKey(key);
    return hasKey == null ? false : hasKey;
  }

  @Override
  public boolean deleteKey(String key) {
    Boolean deleteKey = redisTemplate.delete(key);
    return deleteKey == null ? false : deleteKey;
  }

  @Override
  public void setKeyValue(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }


  @Override
  public void setKeyValueTimeout(String key, Object value) {
    redisTemplate.opsForValue().set(key, value, 300L, TimeUnit.SECONDS);
  }

  @Override
  public Object getValueByKey(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  @Override
  public void clearAll() {
    Set<String> keys = redisTemplate.keys("*");
    redisTemplate.delete(keys);
  }


}
