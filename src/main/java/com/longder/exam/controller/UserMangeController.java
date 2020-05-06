package com.longder.exam.controller;

import com.longder.exam.service.UserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户管理的控制器
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserMangeController {

    @Resource
    private UserManageService userManageService;

    /**
     * 检查角色
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkRole")
    public String getRoleByUser(String loginName){
        log.debug("ajax获取用户角色，登录名：{}",loginName);
        return userManageService.getRoleNameByLoginName(loginName);
    }

    /**
     * 修改密码
     * @return
     */
    @ResponseBody
    @PostMapping("/changePassword")
    public String changePassword(String newPassword){
        userManageService.changePassword(newPassword);
        return "ok";
    }
}
