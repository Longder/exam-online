package com.longder.exam.controller;

import com.longder.exam.entity.po.SysRole;
import com.longder.exam.entity.po.SysUser;
import com.longder.exam.service.UserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 学生管理的控制器
 */
@Slf4j
@Controller
@RequestMapping("/student")
public class StudentManageController {

    @Resource
    private UserManageService userManageService;

    /**
     * 学生列表查询
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("studentList",userManageService.listStudent());
        return "user/student/student-list";
    }

    /**
     * 去添加学生页
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddStudent(){
        return "user/student/create-student-modal";
    }

    /**
     * 添加学生
     * @return
     */
    @PostMapping("/add")
    public String addStudent(SysUser student){
        userManageService.addUser(student, SysRole.ROLE_STUDENT);
        return "redirect:/student/list";
    }

    /**
     * 去修改学生页
     * @return
     */
    @GetMapping("/toUpdate")
    public String toUpdateStudent(Model model,Long studentId){
        SysUser student = userManageService.getOneUser(studentId);
        model.addAttribute("student",student);
        return "user/student/update-student-modal";
    }

    /**
     * 修改学生
     * @return
     */
    @PostMapping("/update")
    public String updateStudent(SysUser student){
        userManageService.updateUser(student);
        return "redirect:/student/list";
    }

    /**
     * 删除学生
     * @return
     */
    @GetMapping("/delete")
    public String deleteStudent(Long studentId){
        log.debug("开始删除学生，学生id:{}",studentId);
        userManageService.deleteUser(studentId);
        return "redirect:/student/list";
    }
}
