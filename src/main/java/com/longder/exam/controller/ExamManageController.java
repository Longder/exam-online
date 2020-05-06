package com.longder.exam.controller;

import com.longder.exam.entity.po.Exam;
import com.longder.exam.security.SecurityUtil;
import com.longder.exam.service.ExamManageService;
import com.longder.exam.service.PaperManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 考试管理相关的控制器
 */
@Slf4j
@Controller
@RequestMapping("/exam")
public class ExamManageController {
    @Resource
    private PaperManageService paperManageService;
    @Resource
    private ExamManageService examManageService;

    /**
     * 试卷选择页
     * @return
     */
    @GetMapping("/toChoose")
    public String toChoosePaper(Model model){
        model.addAttribute("examPaperList",paperManageService.listExamPaper());
        return "exam/choose-exam";
    }

    /**
     * 开始考试页面
     * @return
     */
    @GetMapping("/startExam")
    public String startExam(Long examPaperId,Model model){
        model.addAttribute("exam",
                examManageService.initExam(examPaperId, Objects.requireNonNull(SecurityUtil.getCurrentUser()).getId()));
        return "exam/exam-paper";
    }

    /**
     * 完成考试
     * @return
     */
    @PostMapping("/completeExam")
    public String completeExam(Exam exam,Model model){
        exam = examManageService.completeExam(exam);
        model.addAttribute("exam",exam);
        return "exam/exam-result";
    }

    /**
     * 学生的个人考试列表
     * @return
     */
    @GetMapping("/listExamForStudent")
    public String examListForStudent(Model model){
        model.addAttribute("examList",examManageService.listExamForStudent());
        return "exam/exam-list-for-student";
    }

    /**
     * 考试详情
     * @return
     */
    @GetMapping("/detail")
    public String examDetail(Long examId,Model model){
        model.addAttribute("exam",examManageService.getExam(examId));
        return "/exam/exam-result";
    }

    /**
     * 老师查看的考试列表
     * @return
     */
    @GetMapping("/listExamForTeacher")
    public String examListForTeacher(Model model){
        model.addAttribute("examList",examManageService.listExamForTeacher());
        return "exam/exam-list-for-teacher";
    }

    /**
     * 去阅卷
     * @return
     */
    @GetMapping("/toCheckExam")
    public String toCheckExam(Long examId,Model model){
        model.addAttribute("exam",examManageService.getExam(examId));
        return "/exam/exam-check";
    }

    /**
     * 提交阅卷
     * @return
     */
    @PostMapping("/checkExam")
    public String checkExam(Exam exam){
        examManageService.checkExam(exam);

        return "redirect:/exam/listExamForTeacher";
    }

    /**
     * 验证考试
     * @param examPaperId
     * @return
     */
    @ResponseBody
    @PostMapping("/validExam")
    public String validExam(Long examPaperId){
        return examManageService.validExam(examPaperId);
    }

}
