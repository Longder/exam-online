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
import org.apache.struts2.convention.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * 题目管理相关Action
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/question")
@ParentPackage("json-default")
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
    private Long courseId;
    private String result;
    private Long questionId;
    private String keyWord;

    private File upload;

    @Resource
    private CourseManageService courseManageService;
    @Resource
    private QuestionManageService questionManageService;

    /**
     * 题目列表页
     *
     * @return
     */
    @Action(value = "list", results = {@Result(name = SUCCESS, location = "/question/question-list.jsp")})
    public String list() {
        logger.info("进入题目列表页");
        logger.info("关键字：{}",keyWord);
        courseList = courseManageService.listCourseByCurrentTeacher();
        questionList = questionManageService.listQuestion(keyWord);
        return SUCCESS;
    }

    /**
     * 去添加页
     *
     * @return
     */
    @Action(value = "toAdd", results = {@Result(name = SUCCESS, location = "/question/create-question-modal.jsp")})
    public String toAdd() {
        logger.info("去修添加题目页");
        courseList = courseManageService.listCourseByCurrentTeacher();
        return SUCCESS;
    }

    /**
     * 添加
     *
     * @return
     */
    @Action(value = "add", results = {@Result(name = SUCCESS, type = REDIRECT, location = "list")})
    public String add() {
        logger.info("添加题目表单提交");
        logger.info("数据：{},{}", question.getCourse().getId(), question.getDifficulty());
        questionManageService.saveQuestion(question);
        return SUCCESS;
    }

    /**
     * 批量上传导入题目
     *
     * @return
     */
    @Action(value = "upload", results = {@Result(name = SUCCESS, type = REDIRECT, location = "list")},interceptorRefs={
            @InterceptorRef(
                    params={"allowedTypes","application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                            "maximumSize","1000000"},
                    value="fileUpload"
            ),
            @InterceptorRef("defaultStack"),
            @InterceptorRef("validation")
    })
    public String upload() {
        logger.info("进入文件上传Action");
        logger.info("课程id：{}",courseId);
        questionManageService.importQuestionsFormExcel(courseId,upload);
        return SUCCESS;
    }

    /**
     * 删除题目
     * @return
     */
    @Action(value = "delete", results = {
            @Result(name = "ajax", type = "json", params = { "root", "result" }) })
    public String delete(){
        logger.debug("删除一个题目,题目id:{}",questionId);
        result = questionManageService.deleteOneQuestion(questionId);
        return "ajax";
    }

    /**
     * 去修改题目
     * @return
     */
    @Action(value = "toUpdate", results = {@Result(name = SUCCESS, location = "/question/update-question-modal.jsp")})
    public String toUpdate(){
        logger.debug("去修改题目,题目id:{}",questionId);
        courseList = courseManageService.listCourseByCurrentTeacher();
        question = questionManageService.getOneQuestion(questionId);
        return SUCCESS;
    }

    /**
     * 修改题目
     * @return
     */
    @Action(value = "update", results = {@Result(name = SUCCESS, type = REDIRECT, location = "list")})
    public String update(){
        logger.debug("开始修改题目。");
        questionManageService.saveQuestion(question);
        return SUCCESS;
    }
}
