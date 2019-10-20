package org.zuoyu.service.impl;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.zuoyu.service.IVerificationCodeService;

/**
 * 验证码服务实现.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-10-08 14:43
 **/
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
class VerificationCodeServiceImpl implements IVerificationCodeService {


  private ScheduledExecutorService executorService;

  /**
   * 验证码
   */
  private String verificationCode;


  @Override
  public boolean isPresence() {
    return this.verificationCode != null
        && !"".equals(this.verificationCode)
        && !this.verificationCode.isEmpty();
  }

  @Override
  public String creatVerificationCode() {
    if (isPresence()) {
      return this.verificationCode;
    }
    double math = Math.random();
    this.verificationCode = Double.toString(math).substring(2, 8);
    ThreadFactory threadFactory = new BasicThreadFactory.Builder()
        .namingPattern("timerTask-%d").daemon(true).build();
    this.executorService = new ScheduledThreadPoolExecutor(1, threadFactory);
//    只执行一次
    this.executorService.schedule(this::clearVerificationCode, 300, TimeUnit.SECONDS);
    return this.verificationCode;
  }

  @Override
  public String getVerificationCode() {
    return this.verificationCode;
  }

  @Override
  public void clearVerificationCode() {
    this.verificationCode = null;
    if (this.executorService.isShutdown()) {
      return;
    }
    this.executorService.shutdown();
  }

}
