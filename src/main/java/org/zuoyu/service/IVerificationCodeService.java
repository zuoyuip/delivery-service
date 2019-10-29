package org.zuoyu.service;

/**
 * 验证码服务.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-08 14:43
 **/
public interface IVerificationCodeService {


  /**
   * @param userPhone - 用户
   * 是否存在验证码
   * @return yes / on
   */
  boolean isPresence(String userPhone);

  /**
   * @param userPhone - 用户
   * 创建一个验证码（时效5分钟）
   * @return - 验证码
   */
  String creatVerificationCode(String userPhone);


  /**
   * @param userPhone - 用户
   * 获取该验证码
   * @return - 验证码
   */
  String getVerificationCode(String userPhone);

  /**
   * @param userPhone - 用户
   * 清理验证码
   */
  void clearVerificationCode(String userPhone);

}
