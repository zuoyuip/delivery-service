package org.zuoyu.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.Duration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis的缓存配置.
 *
 * @author zuoyu
 **/
public class CacheConfig extends CachingConfigurerSupport {

  /**
   * 自定义主键生成策略
   */
  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return (target, method, params) -> {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(target.getClass().getName());
      stringBuilder.append(".");
      stringBuilder.append(method.getName());
      for (Object object : params) {
        stringBuilder.append(object.toString());
      }
      return stringBuilder.toString();
    };
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    return objectMapper;
  }

  @Bean
  public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer(
      ObjectMapper objectMapper) {
    return new GenericJackson2JsonRedisSerializer(objectMapper);
  }

  @Bean
  public StringRedisSerializer stringRedisSerializer() {
    return StringRedisSerializer.UTF_8;
  }


  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      LettuceConnectionFactory lettuceConnectionFactory,
      GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer,
      StringRedisSerializer stringRedisSerializer) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(lettuceConnectionFactory);
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory,
      GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer,
      StringRedisSerializer stringRedisSerializer) {
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMillis(3600000))
        .serializeKeysWith(
            RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
        .disableCachingNullValues()
        .disableKeyPrefix();
    return RedisCacheManager
        .builder(RedisCacheWriter.nonLockingRedisCacheWriter(lettuceConnectionFactory))
        .cacheDefaults(redisCacheConfiguration)
        .build();
  }
}
