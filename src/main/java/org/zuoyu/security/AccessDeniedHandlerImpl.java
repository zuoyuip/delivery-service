package org.zuoyu.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

/**
 * 用来解决认证过的用户访问无权限资源时的异常.
 *
 * @author zuoyu
 **/
@Service("accessDeniedHandler")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException {
    response.setContentType("application/json;charset=utf-8");
    PrintWriter responseWriter = response.getWriter();
    responseWriter.print(
        "{\"status\":401,\"message\":\"" + "对不起，您没有访问权限" + "\"}");
    responseWriter.flush();
  }
}
