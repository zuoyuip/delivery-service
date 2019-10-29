package org.zuoyu.service.impl;

import org.springframework.stereotype.Service;
import org.zuoyu.service.IRedisService;
import org.zuoyu.service.IVerificationCodeService;

/**
 * 验证码服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-08 14:43
 **/
@Service
class VerificationCodeServiceImpl implements IVerificationCodeService {

  private final IRedisService iRedisService;

  public VerificationCodeServiceImpl(
      IRedisService iRedisService) {
    this.iRedisService = iRedisService;
  }


  @Override
  public boolean isPresence(String userPhone) {
    return iRedisService.isExists(userPhone);
  }

  @Override
  public String creatVerificationCode(String userPhone) {

    String verificationCode = Double.toString(Math.random()).substring(2, 8);
    iRedisService.setKeyValueTimeout(userPhone, verificationCode);

//    ThreadFactory threadFactory = new BasicThreadFactory.Builder()
//        .namingPattern("timerTask-%d").daemon(true).build();
//    this.executorService = new ScheduledThreadPoolExecutor(1, threadFactory);
////    只执行一次
//    this.executorService.schedule(this::clearVerificationCode, 300, TimeUnit.SECONDS);
    return verificationCode;
  }

  @Override
  public String getVerificationCode(String userPhone) {
    return (String) iRedisService.getValueByKey(userPhone);
  }

  @Override
  public void clearVerificationCode(String userPhone) {
    iRedisService.deleteKey(userPhone);
  }

}
