package com.longder.exam.action.course;

import com.longder.exam.action.BaseAction;
import com.longder.exam.entity.po.Course;
import com.longder.exam.service.CourseManageService;
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
 * 课程管理相关的Action
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/course")
public class CourseAction extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Field
     */
    private Course course;
    private Long courseId;
    private List<Course> courseList;

    @Resource
    private CourseManageService courseManageService;


    /**
     * 课程列表页
     * @return
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/course/course-list.jsp")})
    public String list(){
        logger.debug("进入课程列表页");
        courseList = courseManageService.listCourse();
        return SUCCESS;
    }

    /**
     * 去添加页
     * @return
     */
    @Action(value = "toAdd",results = {@Result(name = SUCCESS,location = "/course/create-course-modal.jsp")})
    public String toAdd(){
        logger.debug("去添加科目页");
        return SUCCESS;
    }


    /**
     * 添加
     * @return
     */
    @Action(value = "add",results = {@Result(name = SUCCESS,type = REDIRECT,location = "list")})
    public String add(){
        logger.debug("添加课程表单提交");
        logger.debug("数据：{},{}",course.getName(),course.getDescription());
        courseManageService.saveCourse(course);
        return SUCCESS;
    }

    /**
     * 去修改页
     * @return
     */
    @Action(value = "toUpdate",results = {@Result(name = SUCCESS,location = "/course/update-course-modal.jsp")})
    public String toUpdate(){
        logger.debug("去修改科目页,科目id:{}",courseId);
        course = courseManageService.getCourse(courseId);
        return SUCCESS;
    }

    /**
     * 修改课程
     * @return
     */
    @Action(value = "update",results = {@Result(name = SUCCESS,type = REDIRECT,location = "list")})
    public String update(){
        logger.debug("开始修改课程，id:{}",course.getId());
        courseManageService.saveCourse(course);
        return SUCCESS;
    }

}
