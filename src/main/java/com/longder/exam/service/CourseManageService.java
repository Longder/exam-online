package com.longder.exam.service;

import com.longder.exam.entity.po.Course;

import java.util.List;

/**
 * Created by Longder
 */
public interface CourseManageService {

    /**
     * 科目列表
     * @return
     */
    List<Course> listCourse();

    /**
     * 当前登录教师下的课程
     * @return
     */
    List<Course> listCourseByCurrentTeacher();


    /**
     * 存储科目，新增和修改都走这个
     * @param course
     */
    void saveCourse(Course course);

    /**
     * 获取一个课程
     * @param courseId
     * @return
     */
    Course getCourse(Long courseId);
}
