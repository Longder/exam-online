package com.longder.exam.action.user;

import com.longder.exam.service.UserManageService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/user")
@ParentPackage("json-default")
public class UserManageAction extends ActionSupport {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * field
     */
    private String loginName;
    private String role;
    private String newPassword;
    private String result;

    @Resource
    private UserManageService userManageService;

    @Action(value = "checkRole", results = {
            @Result(name = "ajax", type = "json", params = { "root", "role" }) })
    public String getRoleByUser(){
        logger.debug("ajax获取用户角色,登陆名:{}",loginName);
        role = userManageService.getRoleNameByLoginName(loginName);
        return "ajax";
    }

    @Action(value = "changePassword", results = {
            @Result(name = "ajax", type = "json", params = { "root", "result" }) })
    public String changePassword(){
        userManageService.changePassword(newPassword);
        result = "ok";
        return "ajax";
    }
}
