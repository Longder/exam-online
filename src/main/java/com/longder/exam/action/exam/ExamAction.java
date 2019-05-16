package com.longder.exam.action.exam;

import com.longder.exam.action.BaseAction;
import com.longder.exam.entity.dto.PaperGeneratorObject;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.Exam;
import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.security.SecurityUtil;
import com.longder.exam.service.ExamManageService;
import com.longder.exam.service.PaperManageService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 考试相关的Action
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Namespace("/exam")
@ParentPackage("json-default")
public class ExamAction extends BaseAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * field
     */
    private List<ExamPaper> examPaperList;
    private Long examPaperId;
    private Exam exam;
    private List<Exam> examList;
    private Long examId;
    private String result;
    private String source;


    @Resource
    private PaperManageService paperManageService;
    @Resource
    private ExamManageService examManageService;

    /**
     * 试卷选择页
     * @return
     */
    @Action(value = "toChoose",results = {@Result(name = SUCCESS,location = "/exam/choose-exam.jsp")})
    public String toChoosePaper(){
        examPaperList = paperManageService.listExamPaper();
        return SUCCESS;
    }

    /**
     * 开始考试页面
     * @return
     */
    @Action(value = "startExam",results = {@Result(name = SUCCESS,location = "/exam/exam-paper.jsp")})
    public String startExam(){
        logger.info("开始考试，选择的试卷Id：{}",examPaperId);
        exam = examManageService.initExam(examPaperId, Objects.requireNonNull(SecurityUtil.getCurrentUser()).getId());
        return SUCCESS;
    }

    /**
     * 完成考试
     * @return
     */
    @Action(value = "completeExam",results = {@Result(name = SUCCESS,location = "/exam/exam-result.jsp")})
    public String completeExam(){
        logger.info("完成考试，考试id：{}",exam.getId());
        exam = examManageService.completeExam(exam);
        return SUCCESS;
    }

    /**
     * 学生的个人考试列表
     * @return
     */
    @Action(value = "listExamForStudent",
            results = {@Result(name = SUCCESS,location = "/exam/exam-list-for-student.jsp")})
    public String examListForStudent(){
        examList = examManageService.listExamForStudent();
        return SUCCESS;
    }

    /**
     * 考试详情
     * @return
     */
    @Action(value = "detail",results = {@Result(name = SUCCESS,location = "/exam/exam-result.jsp")})
    public String examDetail(){
        logger.info("考试详情,考试id:{}",examId);
        System.out.println(source);
        exam = examManageService.getExam(examId);
        return SUCCESS;
    }

    /**
     * 老师查看的考试列表
     * @return
     */
    @Action(value = "listExamForTeacher",
            results = {@Result(name = SUCCESS,location = "/exam/exam-list-for-teacher.jsp")})
    public String examListForTeacher(){
        examList = examManageService.listExamForTeacher();
        return SUCCESS;
    }

    /**
     * 去阅卷
     * @return
     */
    @Action(value = "toCheckExam",results = {@Result(name = SUCCESS,location = "/exam/exam-check.jsp")})
    public String toCheckExam(){
        logger.debug("去阅卷页面，考试id:{}",examId);
        exam = examManageService.getExam(examId);
        return SUCCESS;
    }

    /**
     * 提交阅卷
     * @return
     */
    @Action(value = "checkExam",results = {@Result(name = SUCCESS,type = REDIRECT,location = "listExamForTeacher")})
    public String checkExam(){
        logger.debug("提交了阅卷表单");
        examManageService.checkExam(exam);
        return SUCCESS;
    }

    /**
     * 验证考试
     * @return
     */
    @Action(value = "validExam", results = {
            @Result(name = "ajax", type = "json", params = { "root", "result" }) })
    public String validExam(){
        result = examManageService.validExam(examPaperId);
        return "ajax";
    }


}
