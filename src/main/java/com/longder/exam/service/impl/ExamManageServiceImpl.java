package com.longder.exam.service.impl;

import com.longder.exam.entity.po.*;
import com.longder.exam.repository.*;
import com.longder.exam.service.ExamManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Longder
 */
@Service
public class ExamManageServiceImpl implements ExamManageService {
    @Resource
    private ExamPaperRepository examPaperRepository;
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private ExamRepository examRepository;
    @Resource
    private ExamPaperQuestionRepository examPaperQuestionRepository;
    @Resource
    private ExamDetailRepository examDetailRepository;
    /**
     * 初始化考试
     *
     * @param examPaperId
     * @param studentId
     * @return
     */
    @Override
    @Transactional
    public Exam initExam(Long examPaperId, Long studentId) {
        ExamPaper examPaper = examPaperRepository.getOne(examPaperId);
        SysUser student = sysUserRepository.getOne(studentId);

        Exam exam = new Exam();
        exam.setGrade(0D);
        exam.setExamPaper(examPaper);
        exam.setCourse(examPaper.getCourse());
        exam.setStudent(student);
        examRepository.save(exam);

        //处理考试详情
        List<ExamPaperQuestion> paperQuestionList = examPaperQuestionRepository.listByExamPaper(examPaper);

        List<ExamDetail> examDetailList = new ArrayList<>();
        paperQuestionList.forEach(examPaperQuestion -> {
            ExamDetail detail = new ExamDetail();
            detail.setExamId(exam.getId());
            detail.setQuestion(examPaperQuestion.getQuestion());
            examDetailList.add(detail);
        });

        examDetailRepository.saveAll(examDetailList);
        exam.setDetailList(examDetailList);
        return exam;
    }
}
