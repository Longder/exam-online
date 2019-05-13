package com.longder.exam.entity.po;

import com.longder.exam.entity.enumeration.DifficultyType;
import com.longder.exam.entity.enumeration.QuestionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 题目实体
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "QUESTION")
public class Question extends BaseIdEntity{
    /**
     * 题目内容
     */
    @Column(name = "content_")
    private String content;
    /**
     * 题目类型
     */
    @Column(name = "type_")
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    /**
     * 难度
     */
    @Column(name = "difficulty_")
    private Integer difficulty;

    /**
     * 分值
     */
    @Column(name = "score_")
    private Double score;
    /**
     * 答案
     */
    @Column(name = "answer_")
    private String answer;


    /**
     * 所属课程
     */
    @ManyToOne
    @JoinColumn(name = "course_id_")
    private Course course;

    @Transient
    private String choiceA;
    @Transient
    private String choiceB;
    @Transient
    private String choiceC;
    @Transient
    private String choiceD;

}
