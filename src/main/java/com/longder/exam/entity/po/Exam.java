package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 考试实体
 * Created by Longder
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "EXAM")
@Proxy(lazy = false)
public class Exam extends BaseIdEntity{
    /**
     * 参加考试的学生
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id_")
    private SysUser student;

    /**
     * 所属课程
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id_")
    private Course course;
    /**
     * 试卷
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_paper_id_")
    private ExamPaper examPaper;
    /**
     * 成绩
     */
    @Column(name = "grade_")
    private Double grade;
    /**
     * 是否完成
     */
    @Column(name = "is_complete_")
    private Boolean isComplete = false;

    /**
     * 是否阅卷
     */
    @Column(name = "is_checked_")
    private Boolean isChecked = false;
    /**
     * 考试详情
     */
    @Transient
    List<ExamDetail> detailList;
    /**
     * 考试时间（秒数）
     */
    @Transient
    private Integer examTime;

    /**
     * 分数
     */
    @Transient
    private Double score;

    /**
     * 计算总分
     */
    public void countScore(){
        AtomicReference<Double> result = new AtomicReference<>((double) 0);
        detailList.forEach(examDetail -> {
            if(examDetail.getCorrect()){
                result.updateAndGet(v -> v + examDetail.getQuestion().getScore());
            }
        });
        this.score = result.get();
    }

}
