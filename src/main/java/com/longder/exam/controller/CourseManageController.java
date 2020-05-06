package com.longder.exam.controller;

import com.longder.exam.entity.po.Course;
import com.longder.exam.service.CourseManageService;
import com.longder.exam.service.UserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 课程管理业务的控制器
 */
@Slf4j
@Controller
@RequestMapping("/course")
public class CourseManageController {

    @Resource
    private CourseManageService courseManageService;
    @Resource
    private UserManageService userManageService;

    /**
     * 课程列表页
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("courseList",courseManageService.listCourse());
        return "course/course-list";
    }

    /**
     * 去添加课程页
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model){
        model.addAttribute("teacherList",userManageService.listTeacher());
        return "course/create-course-modal";
    }

    /**
     * 添加课程
     * @return
     */
    @PostMapping("/add")
    public String add(Course course){
        courseManageService.saveCourse(course);
        return "redirect:/course/list";
    }

    /**
     * 去修改课程页
     * @return
     */
    @GetMapping("/toUpdate")
    public String toUpdate(Model model,Long courseId){
        model.addAttribute("course",courseManageService.getCourse(courseId));
        model.addAttribute("teacherList",userManageService.listTeacher());
        return "course/update-course-modal";
    }

    /**
     * 修改课程
     * @return
     */
    @PostMapping("/update")
    public String update(Course course){
        courseManageService.saveCourse(course);
        return "redirect:/course/list";
    }
}
