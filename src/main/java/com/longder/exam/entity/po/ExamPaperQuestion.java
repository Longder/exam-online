package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 试卷和题目的关联表
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exam_paper_question")
@Data
public class ExamPaperQuestion extends BaseIdEntity{
    /**
     * 试卷
     */
    @ManyToOne
    @JoinColumn(name = "exam_paper_id_")
    private ExamPaper examPaper;
    /**
     * 题目
     */
    @ManyToOne
    @JoinColumn(name = "question_id_")
    private Question question;
}
