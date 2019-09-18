package org.zuoyu.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zuoyu.exception.CustomException;
import org.zuoyu.model.User;

/**
 * User的用户工具类.
 *
 * @author zuoyu
 * @program delivery-service
 * @create 2019-09-17 17:07
 **/
public class UserUtil {

  private UserUtil() {
  }

  /**
   * 获取当前用户
   *
   * @return - 当前用户
   */
  public static User currentUser() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    if (authentication == null) {
      throw new CustomException("当前无用户");
    }
    User user = (User) authentication.getPrincipal();
    if (user == null) {
      throw new CustomException("无法获取该用户");
    }
    return user;
  }

  /**
   * 当前是否被登录
   *
   * @return - true/false
   */
  public static boolean isAuthenticated() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    return !(authentication instanceof AnonymousAuthenticationToken);
  }
}
