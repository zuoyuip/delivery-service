package org.zuoyu.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zuoyu.exception.CustomException;
import org.zuoyu.manager.DeliveryManager;
import org.zuoyu.model.Delivery;
import org.zuoyu.service.IDeliveryService;
import org.zuoyu.service.IRedisService;

/**
 * 包裹业务服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-15 11:57
 **/
@Service
class DeliveryServiceImpl implements IDeliveryService {

  private final DeliveryManager deliveryManager;
  private final IRedisService iRedisService;

  DeliveryServiceImpl(
      DeliveryManager deliveryManager, IRedisService iRedisService) {
    this.deliveryManager = deliveryManager;
    this.iRedisService = iRedisService;
  }

  @Override
  public List<Delivery> selectDeliveryAll() {
    return this.sortDeliveries(deliveryManager.selectDeliveryAll());
  }

  @Override
  public List<Delivery> selectDeliveryNotReceive() {
    return this.sortDeliveries(deliveryManager.selectDeliveryNotReceive());
  }

  @Override
  public List<Delivery> selectDeliveryReceive() {
    return this.sortDeliveries(deliveryManager.selectDeliveryReceive());
  }

  @Override
  public List<Delivery> listDelivery() {
    return this.sortDeliveries(deliveryManager.listDelivery());
  }

  @Override
  public boolean selectStatusByDeliveryId(String deliveryId) {
    return deliveryManager.selectStatusByDeliveryId(deliveryId);
  }

  @Override
  public List<Delivery> deliveriesMe(String deliveryUserId) {
    if (deliveryUserId == null || "".equals(deliveryUserId.trim()) || deliveryUserId.trim()
        .isEmpty()) {
      return null;
    }
    return this.sortDeliveries(deliveryManager.deliveriesMe(deliveryUserId));
  }

  @Override
  public List<Delivery> deliveriesDeliveryMe(String deliveryDeliveryUserId) {
    if (deliveryDeliveryUserId == null || "".equals(deliveryDeliveryUserId.trim())
        || deliveryDeliveryUserId.trim().isEmpty()) {
      return null;
    }
    return this.sortDeliveries(deliveryManager.deliveriesDeliveryMe(deliveryDeliveryUserId));
  }

  @Override
  public int insertDelivery(Delivery delivery) {
    if (delivery == null) {
      return 0;
    }
    long orderCode = System.currentTimeMillis() + System.currentTimeMillis();
    delivery.setDeliveryDate(new Date()).setDeliveryStatus(false).setDeliveryOrderCode(
        Long.toString(orderCode));
    return deliveryManager.insertDelivery(delivery);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class,
      CustomException.class})
  public int transactionDelivery(String deliveryId, String deliveryDeliveryUserId) {
    if (deliveryDeliveryUserId == null || "".equals(deliveryDeliveryUserId.trim())
        || deliveryDeliveryUserId.trim().isEmpty()) {
      return 0;
    }
    iRedisService.setKeyValue(deliveryId, true);
    Delivery delivery = new Delivery().setDeliveryId(deliveryId).setDeliveryStatus(true)
        .setDeliveryDeliveryUserId(deliveryDeliveryUserId);
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return deliveryManager.transactionDelivery(delivery);
  }

  @Override
  public Delivery getDeliveryById(String deliveryId) {
    if (deliveryId == null || "".equals(deliveryId.trim()) || deliveryId.trim().isEmpty()) {
      return null;
    }
    return deliveryManager.getDeliveryById(deliveryId);
  }

  @Override
  public int cancelDeliveryById(String deliveryId) {
    if (deliveryId == null || "".equals(deliveryId.trim()) || deliveryId.trim().isEmpty()) {
      return 0;
    }
    Delivery delivery = new Delivery().setDeliveryId(deliveryId).setDeliveryStatus(true);
    return deliveryManager.updateDelivery(delivery);
  }

  private List<Delivery> sortDeliveries(List<Delivery> deliveries) {
    if (deliveries == null || deliveries.isEmpty()) {
      return null;
    }
    deliveries.sort(Comparator.comparing(Delivery::getDeliveryDate));
    return deliveries;
  }

}
