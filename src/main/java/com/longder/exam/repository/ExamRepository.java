package com.longder.exam.repository;

import com.longder.exam.entity.po.Exam;
import com.longder.exam.entity.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface ExamRepository extends JpaRepository<Exam,Long> {
    /**
     * 查询某学生下已经完成的考试
     * @param student
     * @return
     */
    @Query("select e from Exam e where e.isComplete = true and e.student = :student")
    List<Exam> listCompletedByUser(@Param(value = "student") SysUser student);

    /**
     * 查询已经完成的考试
     * @return
     */
    @Query("select e from Exam e where e.isComplete = true")
    List<Exam> listCompleted();
}
