package com.longder.exam.service;

import com.longder.exam.entity.po.Exam;

import java.util.List;

/**
 * 考试管理的Service
 * Created by Longder
 */
public interface ExamManageService {
    /**
     * 初始化考试
     * @return
     */
    Exam initExam(Long examPaperId,Long studentId);

    /**
     * 完成考试
     * @param exam
     * @return
     */
    Exam completeExam(Exam exam);

    /**
     * 学生的考试列表
     * @return
     */
    List<Exam> listExamForStudent();

    /**
     * 老师查看的考试列表
     * @return
     */
    List<Exam> listExamForTeacher();

    /**
     * 获取试卷
     * @param examId
     * @return
     */
    Exam getExam(Long examId);

    /**
     * 阅卷
     * @param exam
     */
    void checkExam(Exam exam);
}
