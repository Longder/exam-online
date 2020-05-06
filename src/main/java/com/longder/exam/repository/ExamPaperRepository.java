package com.longder.exam.repository;

import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.entity.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface ExamPaperRepository extends JpaRepository<ExamPaper,Long> {

    /**
     * 根据教师查询试卷
     * @param teacher
     * @return
     */
    @Query("select p from ExamPaper p where p.course.teacher = :teacher")
    List<ExamPaper> listByTeacher(@Param("teacher") SysUser teacher);

    /**
     * 查询已经发布的试卷
     * @return
     */
    @Query("select p from ExamPaper p where p.published = true")
    List<ExamPaper> listPublished();
}
