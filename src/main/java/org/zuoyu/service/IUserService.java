package org.zuoyu.service;

import java.util.List;
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
   * 根据用户手机号查找安全用户
   *
   * @param userPhone - 用户手机号
   * @return - 安全用户
   */
  User getUserDetailsByUserPhone(String userPhone);

  /**
   * 添加一个安全账户
   *
   * @param user - 安全账户
   * @return - 插入的个数
   */
  int insertUser(User user);

  /**
   * 检测是否存在同样的手机帐号
   *
   * @param userPhone - 手机帐号
   * @return - true/false
   */
  boolean isPresenceByUserPhone(String userPhone);

  /**
   * 获取所有的账户信息
   *
   * @return - 账户信息集合
   */
  List<User> listUser();


  /**
   * 根据ID获取一个账户
   *
   * @param userId - 账户ID
   * @return 账户信息
   */
  User getUserById(String userId);

  /**
   * 根据唯一标识符修改账户
   *
   * @param user - 含有ID的账户
   * @param isPasswordEncoder - 是否对密码进行加密
   * @return - 是否成功
   */
  int updateUserById(User user, boolean isPasswordEncoder);

  /**
   * 验证该用户
   * @param passWord - 密码
   * @return - true/false
   */
  boolean verifyUser(String passWord);
}
