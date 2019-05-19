package com.longder.exam.service;

import com.longder.exam.entity.po.Question;

import java.io.File;
import java.util.List;

/**
 * Created by Longder
 */
public interface QuestionManageService {


    /**
     * 查询获取一个题目
     * @param questionId
     * @return
     */
    Question getOneQuestion(Long questionId);

    /**
     * 错题列表
     * @return
     */
    List<Question> listQuestion(String keyWord);

    /**
     * 错题列表
     * @param keyWord
     * @return
     */
    List<Question> listQuestionForMistake(String keyWord);

    /**
     * 存储题目，新增和修改都走这个
     * @param question
     */
    void saveQuestion(Question question);

    /**
     * 从excel导入题目
     */
    void importQuestionsFormExcel(Long courseId,File excelFile);

    /**
     * 删除一个题目
     * @param questionId
     * @return
     */
    String deleteOneQuestion(Long questionId);

    /**
     * 记录统计错题
     */
    void countMistake(Long questionId);
}
