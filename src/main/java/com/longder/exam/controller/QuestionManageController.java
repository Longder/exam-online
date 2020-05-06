package com.longder.exam.controller;

import com.longder.exam.entity.enumeration.DifficultyType;
import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Question;
import com.longder.exam.service.CourseManageService;
import com.longder.exam.service.QuestionManageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * 题目管理业务的控制器
 */
@Slf4j
@Controller
@RequestMapping("/question")
public class QuestionManageController {
    @Resource
    private CourseManageService courseManageService;
    @Resource
    private QuestionManageService questionManageService;

    /**
     * 题目列表页
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(name = "keyWord",required = false) String keyWord, Model model){
        log.info("进入题目列表页");
        log.info("关键字：{}",keyWord);
        model.addAttribute("courseList",courseManageService.listCourseByCurrentTeacher());
        model.addAttribute("questionList",questionManageService.listQuestion(keyWord));
        return "question/question-list";
    }

    /**
     * 错题列表
     * @return
     */
    @RequestMapping("/listForMistake")
    public String listForMistake(@RequestParam(name = "keyWord",required = false) String keyWord,Model model){
        model.addAttribute("questionList",questionManageService.listQuestionForMistake(keyWord));
        return "/question/mistake-question-list";
    }

    /**
     * 去添加题目页
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model){
        model.addAttribute("courseList",courseManageService.listCourseByCurrentTeacher());
        model.addAttribute("questionTypes", QuestionType.values());
        model.addAttribute("difficultyTypes", DifficultyType.values());
        return "question/create-question-modal";
    }

    /**
     * 添加题目
     * @return
     */
    @PostMapping("/add")
    public String add(Question question){
        questionManageService.saveQuestion(question);
        return "redirect:/question/list";
    }

    /**
     * 批量上传导入题目
     * @return
     */
    @SneakyThrows
    @RequestMapping("/upload")
    public String upload(Long courseId, MultipartFile file){
        questionManageService.importQuestionsFormExcel(courseId,file.getInputStream());
        return "redirect:/question/list";
    }

    /**
     * 删除题目
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Long questionId){
        return questionManageService.deleteOneQuestion(questionId);
    }

    /**
     * 去修改题目
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Model model,Long questionId){
        model.addAttribute("questionTypes", QuestionType.values());
        model.addAttribute("difficultyTypes", DifficultyType.values());
        model.addAttribute("courseList",courseManageService.listCourseByCurrentTeacher());
        model.addAttribute("question",questionManageService.getOneQuestion(questionId));
        return "question/update-question-modal";
    }

    /**
     * 修改题目
     * @return
     */
    @RequestMapping("/update")
    public String update(Question question){
        questionManageService.saveQuestion(question);
        return "redirect:/question/list";
    }

    /**
     * 题目详情页面
     * @return
     */
    @GetMapping("/detail")
    public String detail(Long questionId,Model model){
        model.addAttribute("courseList",courseManageService.listCourseByCurrentTeacher());
        model.addAttribute("question",questionManageService.getOneQuestion(questionId));
        return "/question/question-detail-modal";
    }
}
