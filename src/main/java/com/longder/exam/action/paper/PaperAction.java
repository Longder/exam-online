package com.longder.exam.action.paper;

import com.longder.exam.action.BaseAction;
import com.longder.exam.entity.dto.PaperGeneratorObject;
import com.longder.exam.entity.enumeration.DifficultyType;
import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.entity.po.Question;
import com.longder.exam.service.CourseManageService;
import com.longder.exam.service.PaperManageService;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * field
     */
    private List<Course> courseList;
    private PaperGeneratorObject paperGenerator;
    private List<ExamPaper> examPaperList;
    private DifficultyType[] difficultyTypes = DifficultyType.values();
    private Long courseId;
    private Course course;
    private List<Question> questionList;
    private List<Long> questionIds;


    @Resource
    private CourseManageService courseManageService;
    @Resource
    private PaperManageService paperManageService;

    /**
     * 试卷列表页
     * @return
     */
    @Action(value = "list",results = {@Result(name = SUCCESS,location = "/paper/paper-list.jsp")})
    public String list(){
        logger.info("进入试卷列表页");
        examPaperList = paperManageService.listExamPaper();
        courseList = courseManageService.listCourse();
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

    /**
     * 随机生成试卷
     * @return
     */
    @Action(value = "generate",results = {@Result(name = SUCCESS,type = REDIRECT,location = "list")})
    public String generate(){
        logger.info("生成试卷，生成器内容：");
        logger.info(paperGenerator.toString());
        paperManageService.generatePaper(paperGenerator);
        return SUCCESS;
    }

    /**
     * 去手动组卷页
     * @return
     */
    @Action(value = "toManual",results = {@Result(name = SUCCESS,location = "/paper/manual-paper-modal.jsp")})
    public String toManual(){
        logger.info("手动组卷页");
        logger.info("课程id：{}",courseId);
        course = courseManageService.getCourse(courseId);
        questionList = paperManageService.listQuestionForCourse(courseId);
        return SUCCESS;
    }

    @Action(value = "manual",results = {@Result(name = SUCCESS,type = REDIRECT,location = "list")})
    public String manual(){
        logger.info("自动组卷，表单提交：");
        paperManageService.manualPaper(paperGenerator);
        return SUCCESS;
    }
}
