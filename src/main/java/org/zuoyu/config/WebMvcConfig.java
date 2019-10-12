package org.zuoyu.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
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

  @Value("${aliyunSms.AccessKeyId}")
  private String accessKeyId;

  @Value("${aliyunSms.AccessKeySecret}")
  private String accessKeySecret;


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

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public IAcsClient iAcsClient(){
    DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
    return new DefaultAcsClient(profile);
  }
}
