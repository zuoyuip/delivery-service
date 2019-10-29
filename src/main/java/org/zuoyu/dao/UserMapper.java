package org.zuoyu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zuoyu.model.User;
import org.zuoyu.model.vo.CriteriaModel;
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

  /**
   * 待审核的用户信息
   * @return - 用户信息
   */
  List<CriteriaModel> selectWaitCriteria();
}