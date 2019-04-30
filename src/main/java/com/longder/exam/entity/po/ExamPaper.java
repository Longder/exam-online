package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 试卷实体类
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "EXAM_PAPER")
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
}
