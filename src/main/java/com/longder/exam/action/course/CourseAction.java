package com.longder.exam.action.course;

import com.longder.exam.action.BaseAction;
import com.longder.exam.entity.po.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 课程管理相关的Action
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/course")
public class CourseAction extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Course course;


    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/course/course-list.jsp")})
    public String list(){
        logger.info("进入课程列表页");
        return SUCCESS;
    }

    @Action(value = "toAdd",results = {@Result(name = SUCCESS,location = "/course/create-course-modal.jsp")})
    public String toAdd(){
        logger.info("去添加课程页面");
        return SUCCESS;
    }

    @Action(value = "add",results = {@Result(name = SUCCESS,type = REDIRECT,location = "list")})
    public String add(){
        logger.info("添加课程表单提交");
        logger.info("数据：{},{}",course.getName(),course.getDescription());
        return SUCCESS;
    }
}
