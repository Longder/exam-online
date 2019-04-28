package com.longder.exam.action.question;

import com.longder.exam.action.BaseAction;
import com.longder.exam.entity.enumeration.DifficultyType;
import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.Question;
import com.longder.exam.service.CourseManageService;
import com.longder.exam.service.QuestionManageService;
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
 * 题目管理相关Action
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/question")
public class QuestionAction extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * field
     */
    private List<Course> courseList;
    private QuestionType[] questionTypes = QuestionType.values();
    private DifficultyType[] difficultyTypes = DifficultyType.values();
    private Question question;
    private List<Question> questionList;


    @Resource
    private CourseManageService courseManageService;
    @Resource
    private QuestionManageService questionManageService;

    /**
     * 题目列表页
     * @return
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/question/question-list.jsp")})
    public String list(){
        logger.info("进入题目列表页");
        questionList = questionManageService.listQuestion();
        return SUCCESS;
    }

    /**
     * 去添加页
     * @return
     */
    @Action(value = "toAdd",results = {@Result(name = SUCCESS,location = "/question/create-question-modal.jsp")})
    public String toAdd(){
        logger.info("去修添加题目页");
        courseList = courseManageService.listCourse();
        return SUCCESS;
    }

    /**
     * 添加
     * @return
     */
    @Action(value = "add",results = {@Result(name = SUCCESS,type = REDIRECT,location = "list")})
    public String add(){
        logger.info("添加题目表单提交");
        logger.info("数据：{},{}",question.getCourse().getId(),question.getDifficulty());
        questionManageService.saveQuestion(question);
        return SUCCESS;
    }
}
