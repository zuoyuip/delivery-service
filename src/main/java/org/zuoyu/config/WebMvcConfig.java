package org.zuoyu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Web的配置.
 *
 * @author zuoyu
 **/
public class WebMvcConfig extends WebMvcConfigurationSupport {


  /**
   * 跨域
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedMethods(CorsConfiguration.ALL)
        .allowCredentials(true)
        .allowedHeaders(CorsConfiguration.ALL)
        .allowedOrigins(CorsConfiguration.ALL)
        .maxAge(3600L);
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    /*放行swagger*/
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
    super.addResourceHandlers(registry);
  }
}
