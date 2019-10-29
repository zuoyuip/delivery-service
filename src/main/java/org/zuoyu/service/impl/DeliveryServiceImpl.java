package org.zuoyu.service.impl;

import com.aliyuncs.exceptions.ClientException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zuoyu.exception.CustomException;
import org.zuoyu.manager.DeliveryManager;
import org.zuoyu.manager.SendSmsManager;
import org.zuoyu.model.Delivery;
import org.zuoyu.model.User;
import org.zuoyu.model.UserInfo;
import org.zuoyu.service.ICriteriaService;
import org.zuoyu.service.IDeliveryService;
import org.zuoyu.service.IRedisService;
import org.zuoyu.service.IUserService;

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
  private final SendSmsManager sendSmsManager;
  private final IUserService iUserService;
  private final ICriteriaService iCriteriaService;

  DeliveryServiceImpl(
      DeliveryManager deliveryManager, IRedisService iRedisService,
      SendSmsManager sendSmsManager,
      IUserService iUserService, ICriteriaService iCriteriaService) {
    this.deliveryManager = deliveryManager;
    this.iRedisService = iRedisService;
    this.sendSmsManager = sendSmsManager;
    this.iUserService = iUserService;
    this.iCriteriaService = iCriteriaService;
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
  public String transactionDelivery(String deliveryId, String deliveryDeliveryUserId)
      throws ClientException {
    if (deliveryDeliveryUserId == null || "".equals(deliveryDeliveryUserId.trim())
        || deliveryDeliveryUserId.trim().isEmpty()) {
      return null;
    }
    iRedisService.setKeyValue(deliveryId, true);
    Delivery delivery = this.getDeliveryById(deliveryId).setDeliveryStatus(true)
        .setDeliveryDeliveryUserId(deliveryDeliveryUserId);
//    发布者
    User user = iUserService.getUserById(delivery.getDeliveryUserId());
//    代领者
    User deliveryUser = iUserService.getUserById(deliveryDeliveryUserId);
    UserInfo userInfo = iCriteriaService.findUserInfoById(deliveryUser.getUserInfoId());
    int i = deliveryManager.transactionDelivery(delivery);
    if (i < 0) {
      throw new CustomException("服务器内部错误！");
    }
    return sendSmsManager
        .transaction(user.getUserPhone(), userInfo, deliveryUser.getUserPhone());
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

  @Override
  public void cancelDeliveries() {
    deliveryManager.cancelDeliveries();
  }

  private List<Delivery> sortDeliveries(List<Delivery> deliveries) {
    if (deliveries == null || deliveries.isEmpty()) {
      return null;
    }
    deliveries.sort(Comparator.comparing(Delivery::getDeliveryDate));
    return deliveries;
  }

}
