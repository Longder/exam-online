package com.longder.exam.service;

import com.longder.exam.entity.po.Question;

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
     * 存储科目，新增和修改都走这个
     * @param question
     */
    void saveQuestion(Question question);

}
