package org.zuoyu.manager;

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
@CacheConfig(cacheNames = "delivery", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
public class DeliveryManager {

  private final DeliveryMapper deliveryMapper;

  public DeliveryManager(DeliveryMapper deliveryMapper) {
    this.deliveryMapper = deliveryMapper;
  }


  /**
   * 获取所有包裹信息
   *
   * @return - 包裹信息集合
   */
  @Cacheable
  public List<Delivery> selectDeliveryAll() {
    return deliveryMapper.selectAll();
  }

  /**
   * 获取所有未被接收的包裹信息
   *
   * @return - 包裹信息
   */
  @Cacheable
  public List<Delivery> selectDeliveryNotReceive() {
    Example example = new Example(Delivery.class);
    example.setForUpdate(true);
    example.createCriteria().andEqualTo("deliveryStatus", false);
    return deliveryMapper.selectByExample(example);
  }

  /**
   * 获取所有已被接收的包裹信息
   *
   * @return - 包裹信息
   */
  @Cacheable
  public List<Delivery> selectDeliveryReceive() {
    Example example = new Example(Delivery.class);
    example.setForUpdate(true);
    example.createCriteria().andEqualTo("deliveryStatus", true);
    return deliveryMapper.selectByExample(example);
  }

  /**
   * 获取所有未被领取的包裹信息(不含敏感信息）
   *
   * @return - 包裹信息集合
   */
  @Cacheable
  public List<Delivery> listDelivery() {
    return deliveryMapper.listDeliveryIntroduction();
  }

  /**
   * 获取指定用户发布的所有包裹
   *
   * @param deliveryUserId - 用户ID
   * @return - 包裹信息集合
   */
  @Cacheable(unless = "#result.size() < 1 ")
  public List<Delivery> deliveriesMe(String deliveryUserId) {
    Example example = new Example(Delivery.class);
    example.createCriteria().andEqualTo("deliveryUserId", deliveryUserId);
    return deliveryMapper.selectByExample(example);
  }

  /**
   * 获取指定用户代领的所有包裹
   *
   * @param deliveryDeliveryUserId - 用户ID
   * @return - 包裹信息集合
   */
  @Cacheable(unless = "#result.size() < 1 ")
  public List<Delivery> deliveriesDeliveryMe(String deliveryDeliveryUserId) {
    Example example = new Example(Delivery.class);
    example.createCriteria().andEqualTo("deliveryDeliveryUserId", deliveryDeliveryUserId)
        .andEqualTo("deliveryStatus", true);
    return deliveryMapper.selectByExample(example);
  }

  /**
   * 根据唯一标识获取指定的包裹信息
   *
   * @param deliveryId - 包裹的唯一标识
   * @return - 包裹信息
   */
  @Cacheable(unless = "#result == null ")
  public Delivery getDeliveryById(String deliveryId) {
    return deliveryMapper.selectByPrimaryKey(deliveryId);
  }

  /**
   * 添加包裹信息
   *
   * @param delivery - 包裹信息
   * @return - 成功添加的个数
   */
  @CacheEvict(allEntries = true, condition = "#result > 0")
  public int insertDelivery(Delivery delivery) {
    return deliveryMapper.insertSelective(delivery);
  }


  /**
   * 更改包裹信息
   *
   * @param delivery - 包裹信息
   * @return - 成功更改的个数
   */
  @CacheEvict(allEntries = true, condition = "#result > 0")
  public int updateDelivery(Delivery delivery){
    return deliveryMapper.updateByPrimaryKeySelective(delivery);
  }

  /**
   * 订单交接
   *
   * @param delivery - 包裹
   * @return - 成功达成交接的个数
   */
  @CacheEvict(allEntries = true, condition = "#result > 0")
  public int transactionDelivery(Delivery delivery) {
    return deliveryMapper.updateByPrimaryKeySelective(delivery);
  }
}
