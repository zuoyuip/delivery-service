package org.zuoyu.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zuoyu.service.IUserService;

/**
 * UserDetailsService实现.
 *
 * @author zuoyu
 **/
@Slf4j
@Service("userDetailsService")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDetailsServiceImpl implements UserDetailsService {


  private final IUserService iUserService;

  public UserDetailsServiceImpl(IUserService iUserService) {
    this.iUserService = iUserService;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return (UserDetails) iUserService.getUserDetailsByUserPhone(s);
  }
}
