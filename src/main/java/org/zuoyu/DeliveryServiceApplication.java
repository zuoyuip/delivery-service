package org.zuoyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.zuoyu.config.C3P0DataSource;
import org.zuoyu.config.SwaggerConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zuoyu
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "org.zuoyu.dao")
@Import(value = {C3P0DataSource.class, SwaggerConfig.class})
public class DeliveryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeliveryServiceApplication.class, args);
  }

}
