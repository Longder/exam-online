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
 * 教师管理的控制器
 */
@Slf4j
@Controller
@RequestMapping("/teacher")
public class TeacherMangeController {
    @Resource
    private UserManageService userManageService;

    /**
     * 教师列表查询
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("teacherList",userManageService.listTeacher());
        return "user/teacher/teacher-list";
    }

    /**
     * 去添加教师页
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddTeacher(){
        log.debug("去添加教师页");
        return "user/teacher/create-teacher-modal";
    }

    /**
     * 添加教师
     * @return
     */
    @PostMapping("/add")
    public String addTeacher(SysUser teacher){
        userManageService.addUser(teacher, SysRole.ROLE_TEACHER);
        return "redirect:/teacher/list";
    }

    /**
     * 去修改教师
     * @return
     */
    @GetMapping("/toUpdate")
    public String toUpdateTeacher(Long teacherId,Model model){
        log.debug("去修改教师页面，教师id:{}",teacherId);
        model.addAttribute("teacher",userManageService.getOneUser(teacherId));
        return "user/teacher/update-teacher-modal";
    }

    /**
     * 修改教师
     * @return
     */
    @PostMapping("/update")
    public String updateTeacher(SysUser teacher){
        log.debug("开始修改教师，教师id:{}",teacher.getId());
        userManageService.updateUser(teacher);
        return "redirect:/teacher/list";
    }

    /**
     * 删除教师
     * @return
     */
    @GetMapping("/delete")
    public String deleteTeacher(Long teacherId){
        userManageService.deleteUser(teacherId);
        return "redirect:/teacher/list";
    }
}
