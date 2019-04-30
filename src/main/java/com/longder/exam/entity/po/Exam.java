package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * 考试实体
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "EXAM")
public class Exam extends BaseIdEntity{
    /**
     * 参加考试的学生
     */
    @ManyToOne
    @JoinColumn(name = "student_id_")
    private SysUser student;

    /**
     * 所属课程
     */
    @ManyToOne
    @JoinColumn(name = "course_id_")
    private Course course;
    /**
     * 试卷
     */
    @ManyToOne
    @JoinColumn(name = "exam_paper_id_")
    private ExamPaper examPaper;
    /**
     * 成绩
     */
    @Column(name = "grade_")
    private Double grade;
    /**
     * 考试详情
     */
    @Transient
    List<ExamDetail> detailList;
}
