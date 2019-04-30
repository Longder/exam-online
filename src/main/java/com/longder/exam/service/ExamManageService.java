package com.longder.exam.service;

import com.longder.exam.entity.po.Exam;

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
}
