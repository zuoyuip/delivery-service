package org.zuoyu.dao;

import java.util.List;
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
}