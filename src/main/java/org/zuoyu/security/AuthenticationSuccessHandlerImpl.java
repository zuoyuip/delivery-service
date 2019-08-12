package org.zuoyu.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * 登录成功的实现.
 *
 * @author zuoyu
 **/
@Service("authenticationSuccessHandler")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    response.setContentType("application/json;charset=utf-8");
    PrintWriter responseWriter = response.getWriter();
    responseWriter.write("{\"status\":200,\"message\":\"登录成功\"}");
    responseWriter.flush();
    responseWriter.close();
  }
}
