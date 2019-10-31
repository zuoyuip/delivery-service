package org.zuoyu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zuoyu.model.Delivery;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zuoyu
 */
@Repository
public interface DeliveryMapper extends Mapper<Delivery> {

  /**
   * 获取未被接单的包裹信息
   * @return - 包裹的部分简介信息
   */
  List<Delivery> listDeliveryIntroduction();


  /**
   * 根据唯一标识符获取订单的状态
   * @param deliveryId - 唯一标识符
   * @return - 状态
   */
  boolean selectStatusByDeliveryId(@Param("deliveryId")String deliveryId);


  /**
   * 清空所有包裹
   */
  void clearAllDeliveries();
}