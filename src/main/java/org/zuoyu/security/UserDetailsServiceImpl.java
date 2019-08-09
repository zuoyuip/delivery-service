package org.zuoyu.security;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService实现.
 *
 * @author zuoyu
 **/
@Service("userDetailsService")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDetailsServiceImpl implements UserDetailsService {


  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return null;
  }
}
