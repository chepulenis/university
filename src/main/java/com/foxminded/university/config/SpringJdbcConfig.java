package com.foxminded.university.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@ComponentScan({ "com.foxminded.dao", "com.foxminded.service", "com.foxminded.controller" })
@PropertySource("classpath:application.properties")
public class SpringJdbcConfig {

    @Value("${spring.datasource.url}")
    String datatSourceUrl;

    @Bean
    public DataSource dataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(datatSourceUrl);
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() throws NamingException {
        var template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }

}
