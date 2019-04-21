package com.longder.exam.action.user;

import com.longder.exam.action.BaseAction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 * 教师管理的Action
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/teacher")
public class TeacherManageAction extends BaseAction {
    /**
     * 教师列表查询
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/user/teacher-list.jsp")})
    public String list(){
        return SUCCESS;
    }
}
