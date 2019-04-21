package com.longder.exam.configuration;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Struts2和Spring整合配置
 */
@Configuration
public class Struts2Configuration {
    /**
     * 配置Struts2的过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<StrutsPrepareAndExecuteFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new StrutsPrepareAndExecuteFilter());
        //所有请求交给Struts2处理
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("actionPackages","com.longder.exam.action");
        registrationBean.setName("StrutsPrepareAndExecuteFilter");
        return registrationBean;
    }
}
