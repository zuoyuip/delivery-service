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
   * 是否存在验证码
   * @return yes / on
   */
  boolean isPresence();

  /**
   * 创建一个验证码（时效5分钟）
   * @return - 验证码
   */
  String creatVerificationCode();


  /**
   * 获取该验证码
   * @return - 验证码
   */
  String getVerificationCode();

  /**
   * 清理验证码
   */
  void clearVerificationCode();

}
