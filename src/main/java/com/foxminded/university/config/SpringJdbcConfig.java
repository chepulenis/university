package com.foxminded.university.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@ComponentScan({ "com.foxminded.repository", "com.foxminded.service", "com.foxminded.controller" })
@PropertySource("classpath:application.properties")
public class SpringJdbcConfig {

    @Value("${spring.datasource.url}")
    String datatSourceUrl;
    
    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/university");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("qwerty123");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        return driverManagerDataSource;
    }
    

//  @Bean
//  public DataSource dataSource() {
//      JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//      DataSource dataSource = dataSourceLookup.getDataSource(datatSourceUrl);
//      return dataSource;
//  }
    
    @Bean
    public JdbcTemplate jdbcTemplate() throws NamingException {
        var template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }

}
