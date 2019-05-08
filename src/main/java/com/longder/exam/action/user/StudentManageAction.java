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
 * 学生管理的Action
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/student")
public class StudentManageAction extends BaseAction {

    private Logger logger = LoggerFactory.getLogger(StudentManageAction.class);
    /**
     * field
     */
    private SysUser student;
    private List<SysUser> studentList;
    private Long studentId;

    @Resource
    private UserManageService userManageService;

    /**
     * 学生列表查询
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/user/student/student-list.jsp")})
    public String list(){
        logger.debug("进入学生列表页");
        studentList = userManageService.listStudent();
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

    /**
     * 添加学生
     * @return
     */
    @Action(value = "add", results = {@Result(name = SUCCESS, type = REDIRECT, location = "list")})
    public String addStudent(){
        logger.debug("开始添加学生：{}",student.getName());
        userManageService.addUser(student, SysRole.ROLE_STUDENT);
        return SUCCESS;
    }

    /**
     * 去修改学生页
     * @return
     */
    @Action(value = "toUpdate",results = {@Result(name = SUCCESS,location = "/user/student/update-student-modal.jsp")})
    public String toUpdateStudent(){
        logger.debug("去修改学生页，学生id：{}",studentId);
        student = userManageService.getOneUser(studentId);
        return SUCCESS;
    }

    /**
     * 修改学生
     * @return
     */
    @Action(value = "update", results = {@Result(name = SUCCESS, type = REDIRECT, location = "list")})
    public String updateStudent(){
        logger.debug("开始修改学生，学生id:{}",student.getId());
        userManageService.updateUser(student);
        return SUCCESS;
    }

    /**
     * 删除学生
     * @return
     */
    @Action(value = "delete", results = {@Result(name = SUCCESS, type = REDIRECT, location = "list")})
    public String deleteStudent(){
        logger.debug("开始删除学生，学生id:{}",studentId);
        userManageService.deleteUser(studentId);
        return SUCCESS;
    }
}
