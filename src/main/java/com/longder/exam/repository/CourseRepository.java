package com.longder.exam.repository;

import com.longder.exam.entity.po.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 课程（科目）表dao
 * Created by Longder
 */
public interface CourseRepository extends JpaRepository<Course,Long> {
    /**
     * 根据课程名称查询
     * @param name
     * @return
     */
    @Query("select count(c.id) from Course c where c.name = :name")
    int countByName(@Param("name") String name);
}
