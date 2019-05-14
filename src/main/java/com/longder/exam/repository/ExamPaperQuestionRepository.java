package com.longder.exam.repository;

import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.entity.po.ExamPaperQuestion;
import com.longder.exam.entity.po.Question;
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

    /**
     * 检查题目是否在考卷中
     * @param question
     * @return
     */
    @Query("select e from ExamPaperQuestion e where e.question = :question")
    List<ExamPaperQuestion> listByQuestion(@Param("question")Question question);
}
