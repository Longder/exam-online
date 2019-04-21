package com.longder.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExamOnlineApplication extends SpringBootServletInitializer {
    /**
     * 配置支持外置的Tomcat
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ExamOnlineApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExamOnlineApplication.class, args);
    }

}
