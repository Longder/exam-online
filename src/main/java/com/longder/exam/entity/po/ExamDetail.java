package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 考试详情
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "EXAM_DETAIL")
public class ExamDetail extends BaseIdEntity{
    /**
     * 考试id
     */
    @Column(name = "exam_id_")
    private Long examId;
    /**
     * 题目
     */
    @ManyToOne
    @JoinColumn(name = "question_id_")
    private Question question;
    /**
     * 是否正确
     */
    @Column(name = "correct_")
    private Boolean correct;
}
