package org.zuoyu.manager;

import java.util.Date;
import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.zuoyu.dao.DeliveryMapper;
import org.zuoyu.model.Delivery;
import tk.mybatis.mapper.entity.Example;

/**
 * 包裹的通用业务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-15 15:35
 **/
@Component
@CacheConfig(cacheManager = "cacheManager", keyGenerator = "keyGenerator", cacheNames = "delivery")
public class DeliveryManager {

  private final DeliveryMapper deliveryMapper;

  public DeliveryManager(DeliveryMapper deliveryMapper) {
    this.deliveryMapper = deliveryMapper;
  }

  /**
   * 获取所有的包裹信息
   *
   * @return - 包裹信息集合
   */
  @Cacheable
  public List<Delivery> listDelivery() {
    Example deliveryExample = new Example(Delivery.class);
    deliveryExample.createCriteria().andEqualTo("deliveryStatus", false);
    return deliveryMapper.selectByExample(deliveryExample);
  }

  /**
   * 根据唯一标识获取指定的包裹信息
   *
   * @param deliveryId - 包裹的唯一标识
   * @return - 包裹信息
   */
  @Cacheable
  public Delivery getDeliveryById(String deliveryId) {
    return deliveryMapper.selectByPrimaryKey(deliveryId);
  }

  /**
   * 添加包裹信息
   *
   * @param delivery - 包裹信息
   * @return - 成功添加的个数
   */
  @CacheEvict
  public int insertDelivery(Delivery delivery) {
    return deliveryMapper
        .insertSelective(delivery.setDeliveryDate(new Date()).setDeliveryStatus(false));
  }

  /**
   * 订单交接
   *
   * @param deliveryId - 包裹的唯一标识
   * @param deliveryDeliveryUserId - 接单工作者的唯一标识
   * @return - 成功达成交接的个数
   */
  @CacheEvict
  public int transactionDelivery(String deliveryId, String deliveryDeliveryUserId) {
    Delivery delivery = new Delivery().setDeliveryId(deliveryId).setDeliveryStatus(true)
        .setDeliveryDeliveryUserId(deliveryDeliveryUserId);
    return deliveryMapper.updateByPrimaryKeySelective(delivery);
  }
}
