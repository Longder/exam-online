package com.longder.exam.controller;

import com.longder.exam.entity.dto.PaperGeneratorObject;
import com.longder.exam.entity.enumeration.DifficultyType;
import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.service.CourseManageService;
import com.longder.exam.service.PaperManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 试卷管理的控制器
 */
@Slf4j
@Controller
@RequestMapping("/paper")
public class PaperMangeController {
    @Resource
    private CourseManageService courseManageService;
    @Resource
    private PaperManageService paperManageService;

    /**
     * 试卷列表页
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("examPaperList",paperManageService.listExamPaperForCurrentTeacher());
        model.addAttribute("courseList",courseManageService.listCourseByCurrentTeacher());
        return "paper/paper-list";
    }

    /**
     * 去生成试卷页
     * @return
     */
    @GetMapping("/toGenerate")
    public String toGenerate(Model model){
        model.addAttribute("courseList",courseManageService.listCourseByCurrentTeacher());
        model.addAttribute("difficultyTypes", DifficultyType.values());
        return "paper/generate-paper-modal";
    }

    /**
     * 随机生成试卷
     * @return
     */
    @PostMapping("/generate")
    public String generate(PaperGeneratorObject paperGeneratorObject){
        paperManageService.generatePaper(paperGeneratorObject);
        return "redirect:/paper/list";
    }

    /**
     * 去手动组卷页
     * @return
     */
    @GetMapping("/toManual")
    public String toManual(Model model,Long courseId){
        model.addAttribute("course",courseManageService.getCourse(courseId));
        model.addAttribute("questionList",paperManageService.listQuestionForCourse(courseId));
        model.addAttribute("difficultyTypes", DifficultyType.values());
        return "paper/manual-paper-modal";
    }

    /**
     * 手动组卷提交
     * @return
     */
    @PostMapping("/manual")
    public String manual(PaperGeneratorObject paperGeneratorObject){
        paperManageService.manualPaper(paperGeneratorObject);
        return "redirect:/paper/list";
    }

    /**
     * 试卷详情
     * @return
     */
    @GetMapping("/detail")
    public String detail(Model model,Long paperId){
        ExamPaper examPaper = paperManageService.getPaper(paperId);
        model.addAttribute("examPaper",examPaper);
        model.addAttribute("questionList",examPaper.getQuestionList());
        return "paper/paper-detail";
    }

    /**
     * 删除试卷
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Long paperId){
        return paperManageService.deleteOnePaper(paperId);
    }

    /**
     * 发布试卷
     * @return
     */
    @GetMapping("/publish")
    public String publish(Long paperId){
        return "redirect:/paper/list";
    }

}
