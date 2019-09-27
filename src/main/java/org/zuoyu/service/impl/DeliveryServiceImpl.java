package org.zuoyu.service.impl;

import java.util.Date;
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
  public List<Delivery> selectDeliveryAll() {
    return deliveryManager.selectDeliveryAll();
  }

  @Override
  public List<Delivery> selectDeliveryNotReceive() {
    return deliveryManager.selectDeliveryNotReceive();
  }

  @Override
  public List<Delivery> selectDeliveryReceive() {
    return deliveryManager.selectDeliveryReceive();
  }

  @Override
  public List<Delivery> listDelivery() {
    return deliveryManager.listDelivery();
  }

  @Override
  public List<Delivery> deliveriesMe(String deliveryUserId) {
    if (deliveryUserId == null || "".equals(deliveryUserId.trim()) || deliveryUserId.trim()
        .isEmpty()) {
      return null;
    }
    return deliveryManager.deliveriesMe(deliveryUserId);
  }

  @Override
  public List<Delivery> deliveriesDeliveryMe(String deliveryDeliveryUserId) {
    if (deliveryDeliveryUserId == null || "".equals(deliveryDeliveryUserId.trim())
        || deliveryDeliveryUserId.trim().isEmpty()) {
      return null;
    }
    return deliveryManager.deliveriesDeliveryMe(deliveryDeliveryUserId);
  }

  @Override
  public int insertDelivery(Delivery delivery) {
    if (delivery == null) {
      return 0;
    }
    delivery.setDeliveryDate(new Date()).setDeliveryStatus(false);
    return deliveryManager.insertDelivery(delivery);
  }

  @Override
  public int transactionDelivery(String deliveryId, String deliveryDeliveryUserId) {
    if (deliveryId == null || "".equals(deliveryId.trim()) || deliveryId.trim().isEmpty()
        || deliveryDeliveryUserId == null || "".equals(deliveryDeliveryUserId.trim())
        || deliveryDeliveryUserId.trim().isEmpty()) {
      return 0;
    }
    Delivery delivery = new Delivery().setDeliveryId(deliveryId).setDeliveryStatus(true)
        .setDeliveryDeliveryUserId(deliveryDeliveryUserId);
    return deliveryManager.transactionDelivery(delivery);
  }

  @Override
  public Delivery getDeliveryById(String deliveryId) {
    if (deliveryId == null || "".equals(deliveryId.trim()) || deliveryId.trim().isEmpty()) {
      return null;
    }
    return deliveryManager.getDeliveryById(deliveryId);
  }
}
