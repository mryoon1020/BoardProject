package com.study.boardspring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
@MapperScan(basePackages = {"com.study.service"})
public class DatabaseConfig {

    @Autowired
    private ApplicationContext applicationContext;

    static{

    }
}
