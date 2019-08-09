package org.zuoyu.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

/**
 * 解决登录失败的返回.
 *
 * @author zuoyu
 **/
@Service("authenticationFailureHandler")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException {
    response.setContentType("application/json;charset=utf-8");
    PrintWriter responseWriter = response.getWriter();
    if (exception.fillInStackTrace().getClass() == LockedException.class) {
      responseWriter.print("{\"status\":403,\"message\":\"您的账户已被锁定\"}");
    } else if (exception.fillInStackTrace().getClass() == DisabledException.class) {
      responseWriter.print("{\"status\":403,\"message\":\"您的账户已被禁用\"}");
    } else if (exception.fillInStackTrace().getClass() == AccountExpiredException.class) {
      responseWriter.print("{\"status\":403,\"message\":\"您的账户已过期\"}");
    } else if (exception.fillInStackTrace().getClass() == CredentialsExpiredException.class) {
      responseWriter.print("{\"status\":403,\"message\":\"您的凭证已过期\"}");
    } else {
      responseWriter.print("{\"status\":403,\"message\":\"登录失败，密码或帐号错误\"}");
    }
    responseWriter.flush();
  }
}
