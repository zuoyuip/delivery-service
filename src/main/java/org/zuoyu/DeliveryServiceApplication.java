package org.zuoyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.zuoyu.config.C3P0DataSource;
import org.zuoyu.config.CacheConfig;
import org.zuoyu.config.SecurityConfig;
import org.zuoyu.config.SwaggerConfig;
import org.zuoyu.config.WebMvcConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zuoyu
 */
@EnableSwagger2
@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "org.zuoyu.dao")
@Import(value = {C3P0DataSource.class, SwaggerConfig.class,
    SecurityConfig.class, CacheConfig.class, WebMvcConfig.class})
public class DeliveryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeliveryServiceApplication.class, args);
  }

}
