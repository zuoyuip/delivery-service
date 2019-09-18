package org.zuoyu.service;

import java.util.List;
import org.zuoyu.model.Delivery;

/**
 * 包裹业务服务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-15 11:56
 **/
public interface IDeliveryService {


  /**
   * 获取所有包裹信息
   *
   * @return - 包裹信息集合
   */
  List<Delivery> selectDeliveryAll();

  /**
   * 获取所有未被接收的包裹信息
   *
   * @return - 包裹信息
   */
  List<Delivery> selectDeliveryNotReceive();


  /**
   * 获取所有已被接收的包裹信息
   *
   * @return - 包裹信息
   */
  List<Delivery> selectDeliveryReceive();

  /**
   * 获取所有未被接收的包裹信息（不含敏感信息）
   *
   * @return - 包裹信息集合
   */
  List<Delivery> listDelivery();

  /**
   * 获取指定用户发布的所有包裹
   *
   * @param deliveryUserId - 用户ID
   * @return - 包裹信息集合
   */
  List<Delivery> deliveriesMe(String deliveryUserId);

  /**
   * 获取指定用户代领的所有包裹
   *
   * @param deliveryDeliveryUserId - 用户ID
   * @return - 包裹信息集合
   */
  List<Delivery> deliveriesDeliveryMe(String deliveryDeliveryUserId);

  /**
   * 添加包裹信息
   *
   * @param delivery - 包裹信息
   * @return - 添加的个数
   */
  int insertDelivery(Delivery delivery);


  /**
   * 订单交接
   *
   * @param deliveryId - 包裹的唯一标识
   * @param deliveryDeliveryUserId - 接单工作者的唯一标识
   * @return - 成功达成交接的个数
   */
  int transactionDelivery(String deliveryId, String deliveryDeliveryUserId);


  /**
   * 根据唯一标识获取指定的包裹信息
   *
   * @param deliveryId - 包裹的唯一标识
   * @return - 包裹信息
   */
  Delivery getDeliveryById(String deliveryId);
}
