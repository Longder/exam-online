package com.longder.exam.action.user;

import com.longder.exam.action.BaseAction;
import com.longder.exam.entity.po.SysRole;
import com.longder.exam.entity.po.SysUser;
import com.longder.exam.service.UserManageService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师管理的Action
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/teacher")
public class TeacherManageAction extends BaseAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * field
     */
    private List<SysUser> teacherList;
    private SysUser teacher;

    @Resource
    private UserManageService userManageService;

    /**
     * 教师列表查询
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/user/teacher/teacher-list.jsp")})
    public String list(){
        logger.debug("教师列表页");
        teacherList = userManageService.listTeacher();
        return SUCCESS;
    }

    /**
     * 去添加教师页
     * @return
     */
    @Action(value = "toAdd",results = {@Result(name = SUCCESS,location = "/user/teacher/create-teacher-modal.jsp")})
    public String toAddTeacher(){
        logger.debug("去添加教师页");
        return SUCCESS;
    }

    /**
     *添加教师
     * @return
     */
    @Action(value = "add", results = {@Result(name = SUCCESS, type = REDIRECT, location = "list")})
    public String addStudent(){
        logger.debug("开始添加教师：{}",teacher.getName());
        userManageService.saveUser(teacher, SysRole.ROLE_TEACHER);
        return SUCCESS;
    }

}
