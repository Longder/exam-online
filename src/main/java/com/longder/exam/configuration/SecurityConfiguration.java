package com.longder.exam.configuration;

import com.longder.exam.security.CustomerUserDetailsService;
import com.longder.exam.security.FormLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * Spring Security 权限安全配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomerUserDetailsService userDetailsService;

    /**
     * 配置静态资源部被Security拦截
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .and()
                .formLogin().loginPage("/toLogin").loginProcessingUrl("/login_check").permitAll()
                .successHandler(formLoginSuccessHandler())
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/toLogin")
                .and().csrf().disable().headers().cacheControl();

    }

    /**
     * 配置自定义的UserDetailsService
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 密码加密工具
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FormLoginSuccessHandler formLoginSuccessHandler(){
        FormLoginSuccessHandler handler = new FormLoginSuccessHandler();
        handler.setDefaultTargetUrl("/admin/index");
        handler.setAlwaysUseDefaultTargetUrl(true);
        return handler;
    }
}
