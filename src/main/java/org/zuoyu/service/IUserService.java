package org.zuoyu.service;

import org.zuoyu.model.User;

/**
 * 安全用户服务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-08-10 17:59
 **/
public interface IUserService {


  /**
   * 根据用户名称查找安全用户
   *
   * @param userName - 用户名称
   * @return - 安全用户
   */
  User getUserDetailsByUserName(String userName);
}
