package com.longder.exam.repository;

import com.longder.exam.entity.po.Exam;
import com.longder.exam.entity.po.ExamPaper;
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

    /**
     * 查询某老师下课程包括的已经完成的考试
     * @return
     */
    @Query("select e from Exam e where e.isComplete = true and e.course.teacher = :teacher")
    List<Exam> listCompletedByTeacher(@Param("teacher") SysUser teacher);

    /**
     * 根据试卷 查询该试卷下已经完成的考试
     * @return
     */
    @Query("select e from Exam e where e.isComplete = true and e.examPaper = :examPaper")
    List<Exam> listCompletedByPaper(@Param("examPaper") ExamPaper examPaper);

    /**
     * 根据试卷和学生id，查询已完成的考试
     */
    @Query("select e from Exam e where e.isComplete = true and e.examPaper = :examPaper and e.student = :student")
    List<Exam> listCompletedByPaperAndStudent(@Param("examPaper") ExamPaper examPaper,@Param("student") SysUser student);
}
