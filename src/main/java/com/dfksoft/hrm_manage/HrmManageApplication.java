package com.dfksoft.hrm_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.time.LocalTime;

@SpringBootApplication
public class HrmManageApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HrmManageApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HrmManageApplication.class, args);
    }

}
