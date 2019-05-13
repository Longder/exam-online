package com.longder.exam.service;

import com.longder.exam.entity.po.Question;

import java.io.File;
import java.util.List;

/**
 * Created by Longder
 */
public interface QuestionManageService {



    /**
     * 科目列表
     * @return
     */
    List<Question> listQuestion();


    /**
     * 存储题目，新增和修改都走这个
     * @param question
     */
    void saveQuestion(Question question);

    /**
     * 从excel导入题目
     */
    void importQuestionsFormExcel(Long courseId,File excelFile);
}
