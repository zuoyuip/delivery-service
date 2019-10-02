package org.zuoyu.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

/**
 * 解决登录失败的返回.
 *
 * @author zuoyu
 **/
@Slf4j
@Service("authenticationFailureHandler")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    PrintWriter responseWriter = response.getWriter();
    if (exception.fillInStackTrace().getClass() == LockedException.class) {
      writer(responseWriter, "{\"status\":403,\"message\":\"您的账户已被锁定\"}");
      return;
    }
    if (exception.fillInStackTrace().getClass() == DisabledException.class) {
      writer(responseWriter, "{\"status\":403,\"message\":\"您的账户已被禁用\"}");
      return;
    }
    if (exception.fillInStackTrace().getClass() == AccountExpiredException.class) {
      writer(responseWriter, "{\"status\":403,\"message\":\"您的账户已过期\"}");
      return;
    }
    if (exception.fillInStackTrace().getClass() == CredentialsExpiredException.class) {
      writer(responseWriter, "{\"status\":403,\"message\":\"您的凭证已过期\"}");
      return;
    }
    if (exception.fillInStackTrace().getClass() == InternalAuthenticationServiceException.class) {
      writer(responseWriter, "{\"status\":403,\"message\":\"登录失败，密码或帐号错误\"}");
      return;
    }
    if (exception.fillInStackTrace().getClass() == BadCredentialsException.class){
      writer(responseWriter, "{\"status\":403,\"message\":\"登录失败，密码或帐号错误\"}");
      return;
    }
    log.error(exception.getLocalizedMessage(), exception);
    writer(responseWriter, "{\"status\":500,\"message\":\"登录功能异常\"}");
  }

  private void writer(PrintWriter printWriter, String content) {
    printWriter.print(content);
    printWriter.flush();
    printWriter.close();
  }
}
