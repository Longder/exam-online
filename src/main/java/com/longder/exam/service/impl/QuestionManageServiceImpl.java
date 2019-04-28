package com.longder.exam.service.impl;

import com.longder.exam.entity.po.Question;
import com.longder.exam.repository.QuestionRepository;
import com.longder.exam.service.QuestionManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目管理的Service
 * Created by Longder
 */
@Service
public class QuestionManageServiceImpl implements QuestionManageService {

    @Resource
    private QuestionRepository questionRepository;


    /**
     * 题目列表
     *
     * @return
     */
    @Override
    public List<Question> listQuestion() {
        return questionRepository.findAll();
    }

    /**
     * 题存储题目，新增和修改都走这个
     *
     * @param question
     */
    @Override
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }
}
