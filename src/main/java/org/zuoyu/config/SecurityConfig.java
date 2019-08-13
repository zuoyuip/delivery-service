package org.zuoyu.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.zuoyu.security.AccessDeniedHandlerImpl;
import org.zuoyu.security.AuthenticationEntryPointImpl;
import org.zuoyu.security.AuthenticationFailureHandlerImpl;
import org.zuoyu.security.AuthenticationSuccessHandlerImpl;
import org.zuoyu.security.LogoutSuccessHandlerImpl;

/**
 * security的配置.
 *
 * @author zuoyu
 **/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  public SecurityConfig(
      @Qualifier("userDetailsService") UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    super.configure(auth);
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf().disable()
        .requestCache().disable()
//        登录
        .formLogin()
        .loginProcessingUrl("/login")
        .usernameParameter("userName")
        .passwordParameter("passWord")
        .successHandler(new AuthenticationSuccessHandlerImpl())
        .failureHandler(new AuthenticationFailureHandlerImpl())
        .permitAll()
        .and()
//        记住
        .rememberMe()
        .rememberMeParameter("rememberMe")
        .tokenValiditySeconds(31558150)
        .key("zuoyu")
        .and()
//        登出
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(new LogoutSuccessHandlerImpl())
        .permitAll()
        .and()
//        权限处理
        .exceptionHandling()
        .accessDeniedHandler(new AccessDeniedHandlerImpl())
        .authenticationEntryPoint(new AuthenticationEntryPointImpl())
        .and()
//        访问权限处理
        .authorizeRequests()
        .requestMatchers(CorsUtils::isPreFlightRequest)
        .permitAll()
        .antMatchers(HttpMethod.POST, "user")
        .permitAll()
        .antMatchers("/**")
        .permitAll();
    super.configure(http);
  }


  @Bean("passwordEncoder")
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /**
   * 配置跨域
   */
  @Bean("corsConfigurationSource")
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
    corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
    corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
    corsConfiguration.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return urlBasedCorsConfigurationSource;
  }


}
