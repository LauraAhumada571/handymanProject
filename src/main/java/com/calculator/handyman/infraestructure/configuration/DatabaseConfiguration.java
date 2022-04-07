package com.calculator.handyman.infraestructure.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/handyman_db");
        hikariConfig.setUsername("postgres_user");
        hikariConfig.setPassword("postgres_password");

        return new HikariDataSource(hikariConfig);
    }
}
