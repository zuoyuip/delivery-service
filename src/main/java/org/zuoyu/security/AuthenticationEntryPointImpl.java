package org.zuoyu.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

/**
 * 用来解决匿名用户访问无权限资源时的异常.
 *
 * @author zuoyu
 **/
@Service("authenticationEntryPoint")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    response.setContentType("application/json;charset=utf-8");
    PrintWriter responseWriter = response.getWriter();
    responseWriter.write("{\"status\":401,\"message\":\"" + "您还没有登录，请先登录" + "\"}");
    responseWriter.flush();
    responseWriter.close();
  }
}
