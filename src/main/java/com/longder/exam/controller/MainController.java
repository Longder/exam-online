package com.longder.exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 主控制器，处理登陆，登出等。
 */
@Slf4j
@Controller
public class MainController {

    /**
     * 访问默认跳转登录页
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "redirect:toLogin";
    }

    /**
     * 登陆页
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
