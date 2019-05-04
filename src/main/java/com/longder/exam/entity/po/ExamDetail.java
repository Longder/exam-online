package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;

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
     * 作答的答案
     */
    @Column(name = "answer_")
    private String answer = "";
    /**
     * 是否正确
     */
    @Column(name = "correct_")
    private Boolean correct;

    /**
     * 判题
     */
    public void validAnswer(){
        if(!ObjectUtils.isEmpty(question.getAnswer())){//客观题
            this.correct = question.getAnswer().equals(this.answer);
        }
    }
}
