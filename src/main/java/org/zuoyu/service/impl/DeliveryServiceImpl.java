package org.zuoyu.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.zuoyu.manager.DeliveryManager;
import org.zuoyu.model.Delivery;
import org.zuoyu.service.IDeliveryService;

/**
 * 包裹业务服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-15 11:57
 **/
@Service
public class DeliveryServiceImpl implements IDeliveryService {

  private final DeliveryManager deliveryManager;

  public DeliveryServiceImpl(
      DeliveryManager deliveryManager) {
    this.deliveryManager = deliveryManager;
  }

  @Override
  public List<Delivery> listDelivery() {
    return deliveryManager.listDelivery();
  }

  @Override
  public int insertDelivery(Delivery delivery) {
    if (delivery == null){
      return 0;
    }
    return deliveryManager.insertDelivery(delivery);
  }

  @Override
  public int transactionDelivery(String deliveryId, String deliveryDeliveryUserId) {
    if ("".equals(deliveryId) || "".equals(deliveryDeliveryUserId)){
      return 0;
    }
    return deliveryManager.transactionDelivery(deliveryId, deliveryDeliveryUserId);
  }

  @Override
  public Delivery getDeliveryById(String deliveryId) {
    if ("".equals(deliveryId)){
      return null;
    }
    return deliveryManager.getDeliveryById(deliveryId);
  }
}
