package com.longder.exam.action;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/admin")
public class IndexAction extends BaseAction {

    Logger logger = LoggerFactory.getLogger(IndexAction.class);

    @Action(value = "index",results = {@Result(name = SUCCESS,location = "/index.jsp")})
    public String index(){
        logger.debug("进入index");
        return SUCCESS;
    }

    /**
     * 仪表盘
     * @return
     */
    @Action(value = "dashboard",results = {@Result(name = SUCCESS,location = "/dashboard.jsp")})
    public String dashboard(){
        logger.debug("仪表盘页面");
        return SUCCESS;
    }

    /**
     * 去修改密码页
     * @return
     */
    @Action(value = "editPwd",results = {@Result(name = SUCCESS,location = "/user/updatePasswordModal.jsp")})
    public String toEditPassword(){
        return SUCCESS;
    }
}
