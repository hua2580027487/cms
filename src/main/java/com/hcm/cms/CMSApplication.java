package com.hcm.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
@MapperScan(basePackages = {"com.hcm.cms.mapper"})
public class CMSApplication {
//public class CMSApplication extends SpringBootServletInitializer{
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(CMSApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(CMSApplication.class, args);
    }

}
