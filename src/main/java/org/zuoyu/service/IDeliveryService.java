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
   * 获取所有的包裹信息
   *
   * @return - 包裹信息集合
   */
  List<Delivery> listDelivery();

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
   * @param deliveryId - 包裹的唯一标识
   * @return - 包裹信息
   */
  Delivery getDeliveryById(String deliveryId);
}
