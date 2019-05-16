package com.longder.exam.service.impl;

import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.SysUser;
import com.longder.exam.repository.CourseRepository;
import com.longder.exam.security.SecurityUtil;
import com.longder.exam.service.CourseManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Longder
 */
@Service
public class CourseManageServiceImpl implements CourseManageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CourseRepository courseRepository;

    /**
     * 科目列表
     *
     * @return
     */
    @Override
    public List<Course> listCourse() {
        return courseRepository.findAll();
    }

    /**
     * 当前登录教师下的课程
     *
     * @return
     */
    @Override
    public List<Course> listCourseByCurrentTeacher() {
        SysUser teacher = SecurityUtil.getCurrentUser();
        return courseRepository.listByTeacher(teacher);
    }

    /**
     * 存储科目，新增和修改都走这个
     *
     * @param course
     */
    @Override
    public void saveCourse(Course course) {
        logger.info("添加或修改课程的Service方法");
        courseRepository.save(course);
    }

    /**
     * 获取一个课程
     *
     * @param courseId
     * @return
     */
    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.getOne(courseId);
    }
}
