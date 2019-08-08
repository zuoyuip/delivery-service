package org.zuoyu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * C3P0数据源.
 *
 * @author zuoyu
 **/
public class C3P0DataSource {

  @Bean
  @Primary
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  @ConfigurationProperties(prefix = "c3p0.datasource")
  public DataSource dataSource(){
    return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
  }
}
