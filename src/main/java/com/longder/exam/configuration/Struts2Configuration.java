package com.longder.exam.configuration;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * Struts2和Spring整合配置
 */
@Configuration
public class Struts2Configuration {

    @Resource
    private MultipartConfigElement multipartConfigElement;
    @Resource
    private DispatcherServlet dispatcherServlet;

    /**
     * 把SpringMVC的servlet干掉
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean apiServlet() {
        ServletRegistrationBean<DispatcherServlet> bean = new ServletRegistrationBean<>(dispatcherServlet);
        //注入上传配置到自己注册的ServletRegistrationBean
        bean.addUrlMappings("*.test");
        bean.setMultipartConfig(multipartConfigElement);
        bean.setName("dispatcherServlet");
        return bean;
    }

    /**
     * 配置Struts2的过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<StrutsPrepareAndExecuteFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new StrutsPrepareAndExecuteFilter());
        //所有请求交给Struts2处理
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("actionPackages", "com.longder.exam.action");
        registrationBean.setName("StrutsPrepareAndExecuteFilter");
        return registrationBean;
    }
}
