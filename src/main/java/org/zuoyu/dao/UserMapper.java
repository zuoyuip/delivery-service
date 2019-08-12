package org.zuoyu.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zuoyu.model.User;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author zuoyu
 */
@Repository
public interface UserMapper extends Mapper<User> {

  /**
   * 根据账户手机号码查询数据数量
   * @param userPhone - 手机帐号
   * @return - 数据数量
   */
  int countByUserPhone(@Param("userPhone")String userPhone);
}