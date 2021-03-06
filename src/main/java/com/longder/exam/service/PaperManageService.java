package com.longder.exam.service;

import com.longder.exam.entity.dto.PaperGeneratorObject;
import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.entity.po.Question;

import java.util.List;

/**
 * 试卷管理的Service
 * Created by Longder
 */
public interface PaperManageService {

    /**
     * 试卷列表
     * @return
     */
    List<ExamPaper> listExamPaper();

    /**
     * 当前教师下的课程的试卷
     * @return
     */
    List<ExamPaper> listExamPaperForCurrentTeacher();

    /**
     * 生成试卷
     * @param generatorObject
     */
    void generatePaper(PaperGeneratorObject generatorObject);

    /**
     * 获取某课程下的所有题目
     * @param courseId
     * @return
     */
    List<Question> listQuestionForCourse(Long courseId);

    /**
     * 手动组卷
     * @param generatorObject
     */
    void manualPaper(PaperGeneratorObject generatorObject);

    /**
     * 获取试卷
     * @param paperId
     * @return
     */
    ExamPaper getPaper(Long paperId);

    /**
     * 删除试卷
     * @param paperId
     * @return
     */
    String deleteOnePaper(Long paperId);
}
