package com.longder.exam.repository;

import com.longder.exam.entity.po.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程（科目）表dao
 * Created by Longder
 */
public interface CourseRepository extends JpaRepository<Course,Long> {
}
