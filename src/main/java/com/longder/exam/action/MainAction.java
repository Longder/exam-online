package com.longder.exam.action;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主Action,处理登录页,登出页等
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/")
public class MainAction extends BaseAction{
    Logger logger = LoggerFactory.getLogger(MainAction.class);
    /**
     *主页，默认跳转到登陆页
     */
    @Action(value = "",results = {@Result(name = SUCCESS,type = REDIRECT,location = "toLogin")})
    public String index(){
        logger.debug("系统启动");
        return SUCCESS;
    }

    /**
     * 登陆页
     */
    @Action(value = "toLogin",results = {@Result(name = SUCCESS,location = "/login.jsp")})
    public String toLogin(){
        logger.debug("进入登录页Action");
        return SUCCESS;
    }

}
