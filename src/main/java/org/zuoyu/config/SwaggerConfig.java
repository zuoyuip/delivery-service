package org.zuoyu.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置.
 *
 * http://localhost:8080/swagger-ui.html
 * @author zuoyu
 **/
public class SwaggerConfig {

  @Bean
  public Docket restApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors
            .basePackage("org.zuoyu.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("测试swagger2构建API文档")
        .description("简单优雅的restful风格的API文档")
        .termsOfServiceUrl("http://www.zuoyu.top")
        .contact(new Contact("zuoyu", "www.zuoyu.top", "zuoyuip@qq.com"))
        .version("1.0.0")
        .build();
  }
}
