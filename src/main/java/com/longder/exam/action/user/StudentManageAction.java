package com.longder.exam.action.user;

import com.longder.exam.action.BaseAction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学生管理的Action
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/student")
public class StudentManageAction extends BaseAction {

    Logger logger = LoggerFactory.getLogger(StudentManageAction.class);
    /**
     * 学生列表查询
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/user/student/student-list.jsp")})
    public String list(){
        logger.debug("进入学生列表页");
        return SUCCESS;
    }

    /**
     * 去新增学生页
     * @return
     */
    @Action(value = "toAdd",results = {@Result(name = SUCCESS,location = "/user/student/create-student-modal.jsp")})
    public String toAddStudent(){
        logger.debug("去添加学生页");
        return SUCCESS;
    }
}
