package com.longder.exam.repository;

import com.longder.exam.entity.po.ExamDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface ExamDetailRepository extends JpaRepository<ExamDetail,Long> {
    /**
     * 根据考试id获取考试详情
     * @param examId
     * @return
     */
    @Query("select d from ExamDetail d where d.examId = :examId")
    List<ExamDetail> listByExamId(@Param(value = "examId") Long examId);
}
