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
     * 存储科目，新增和修改都走这个
     * @param course
     */
    void saveCourse(Course course);
}
