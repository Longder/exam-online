package com.longder.exam.action.user;

import com.longder.exam.action.BaseAction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 * 学生管理的Action
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/student")
public class StudentManageAction extends BaseAction {

    /**
     * 学生列表查询
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/user/student-list.jsp")})
    public String list(){
        return SUCCESS;
    }
}
