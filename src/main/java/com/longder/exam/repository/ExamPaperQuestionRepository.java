package com.longder.exam.repository;

import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.entity.po.ExamPaperQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface ExamPaperQuestionRepository extends JpaRepository<ExamPaperQuestion,Long> {

    /**
     * 试卷下的题目
     * @param examPaper
     * @return
     */
    @Query("select e from ExamPaperQuestion e where e.examPaper = :examPaper")
    List<ExamPaperQuestion> listByExamPaper(@Param("examPaper") ExamPaper examPaper);
}
