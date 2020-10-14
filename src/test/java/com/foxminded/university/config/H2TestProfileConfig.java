package com.foxminded.university.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;

@Configuration
@Profile("test")
@TestPropertySource
public class H2TestProfileConfig {
    
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("h2.datasource.driver"));
        dataSource.setUrl(env.getProperty("h2.datasource.url"));
        dataSource.setUsername(env.getProperty("h2.datasource.name"));
        dataSource.setPassword(env.getProperty("h2.datasource.password"));
        return dataSource;
    }

}
