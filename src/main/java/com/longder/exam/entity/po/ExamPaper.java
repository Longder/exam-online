package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

/**
 * 试卷实体类
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "EXAM_PAPER")
@Proxy(lazy = false)
public class ExamPaper extends BaseIdEntity{
    /**
     * 所属课程
     */
    @ManyToOne
    @JoinColumn(name = "course_id_")
    private Course course;

    /**
     * 试卷名称
     */
    @Column(name = "name_")
    private String name;
    /**
     * 考试时间（小时）
     */
    @Column(name = "hours_")
    private Integer hours;
    /**
     * 考试时间(分钟)
     */
    @Column(name = "minutes_")
    private Integer minutes;

    /**
     * 是否发布
     * true 发布 false不发布
     */
    @Column(name = "published_")
    private Boolean published;
    /**
     * 选择题数量
     */
    @Transient
    private Integer choiceCount;
    /**
     * 填空题数量
     */
    @Transient
    private Integer fillCount;

    /**
     * 问答题数量
     */
    @Transient
    private Integer askCount;
    /**
     * 简答题数量
     */
    @Transient
    private Integer essayCount;
    /**
     * 试卷中的题目
     */
    @Transient
    private List<Question> questionList;

}
