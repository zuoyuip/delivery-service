package org.zuoyu.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.zuoyu.model.User;
import org.zuoyu.security.constants.JwtConstants;
import org.zuoyu.security.utils.JwtTokenUtils;

/**
 * JWT的权限过滤器.
 *
 * @author zuoyu
 * @program jwt
 * @create 2019-10-17 16:26
 **/
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String token = request.getHeader(JwtConstants.TOKEN_HEADER);
    if (StringUtils.isEmpty(token)) {
      chain.doFilter(request, response);
      return;
    }
    if (JwtTokenUtils.isTokenExpired(token)) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "您的身份已过期，请重新登录");
      return;
    }
    User user = JwtTokenUtils.getUserByToken(token);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        user, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    super.doFilterInternal(request, response, chain);
  }
}
