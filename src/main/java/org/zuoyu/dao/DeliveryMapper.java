package org.zuoyu.dao;

import org.springframework.stereotype.Repository;
import org.zuoyu.model.Delivery;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zuoyu
 */
@Repository
public interface DeliveryMapper extends Mapper<Delivery> {

}