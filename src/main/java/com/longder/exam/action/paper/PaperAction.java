package com.longder.exam.action.paper;

import com.longder.exam.action.BaseAction;
import com.longder.exam.entity.enumeration.DifficultyType;
import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.Question;
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
 * 试卷管理的Action
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/paper")
public class PaperAction extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * field
     */
    private List<Course> courseList;

    @Resource
    private CourseManageService courseManageService;

    /**
     * 试卷列表页
     * @return
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/paper/paper-list.jsp")})
    public String list(){
        logger.info("进入试卷列表页");
        return SUCCESS;
    }

    /**
     * 去生成试卷页
     * @return
     */
    @Action(value = "toGenerate",results = {@Result(name = SUCCESS,location = "/paper/generate-paper-modal.jsp")})
    public String toGenerate(){
        logger.info("去生成试卷！");
        courseList = courseManageService.listCourse();
        return SUCCESS;
    }


    @Action(value = "generate",results = {@Result(name = SUCCESS,type = REDIRECT,location = "list")})
    public String generate(){
        logger.info("生成试卷");
        return SUCCESS;
    }
}
