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
public class ExamAction extends BaseAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * field
     */
    private List<ExamPaper> examPaperList;
    private Long examPaperId;
    private Exam exam;


    @Resource
    private PaperManageService paperManageService;
    @Resource
    private ExamManageService examManageService;

    /**
     * 试卷选择页
     * @return
     */
    @Action(value = "toChoose",results = {@Result(name = SUCCESS,location = "/exam/choose-exam.jsp")})
    public String list(){
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
}
