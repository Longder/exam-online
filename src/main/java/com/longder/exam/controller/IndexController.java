package com.longder.exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制器，基本功能
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class IndexController {
    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        log.debug("进入index");
        return "index";
    }

    /**
     * 仪表盘
     * @return
     */
    @GetMapping("/dashboard")
    public String dashboard(){
        log.debug("仪表盘页面");
        return "dashboard";
    }

    /**
     * 去修改密码页
     * @return
     */
    @GetMapping("/editPwd")
    public String toEditPassword(){
        return "user/updatePasswordModal";
    }

}
