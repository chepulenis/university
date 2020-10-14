package com.foxminded.university.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({ "com.foxminded.repository", "com.foxminded.service", "com.foxminded.controller",
        "com.foxminded.restcontroller" })
@EnableWebMvc
@Profile("dev")
public class SpringJdbcConfig {

    @Value("${spring.datasource.url}")
    String datatSourceUrl;

    @Bean
    public DataSource dataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource(datatSourceUrl);
        return dataSource;
    }

}
