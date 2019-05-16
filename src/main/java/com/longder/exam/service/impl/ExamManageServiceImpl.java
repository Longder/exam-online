package com.longder.exam.service.impl;

import com.longder.exam.entity.po.*;
import com.longder.exam.repository.*;
import com.longder.exam.security.SecurityUtil;
import com.longder.exam.service.ExamManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        //计算考试需要的时间
        Integer seconds = examPaper.getHours() *60*60 + examPaper.getMinutes()*60;
        exam.setExamTime(seconds);
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

    /**
     * 完成考试
     *
     * @param formExam
     * @return
     */
    @Override
    @Transactional
    public Exam completeExam(Exam formExam) {
        //完成考试
        Exam originalExam = examRepository.getOne(formExam.getId());
        originalExam.setIsComplete(true);
        examRepository.save(originalExam);

        List<ExamDetail> examDetailList = new ArrayList<>();
        formExam.getDetailList().forEach(detail -> {
            ExamDetail dbDetail = examDetailRepository.getOne(detail.getId());
            dbDetail.setAnswer(detail.getAnswer());
            //判题
            dbDetail.validAnswer();
            examDetailList.add(dbDetail);
        });
        examDetailRepository.saveAll(examDetailList);
        originalExam.setDetailList(examDetailList);
        return originalExam;
    }

    /**
     * 学生课程列表
     *
     * @return
     */
    @Override
    public List<Exam> listExamForStudent() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        return examRepository.listCompletedByUser(currentUser);
    }

    /**
     * 老师查看的考试列表
     *
     * @return
     */
    @Override
    public List<Exam> listExamForTeacher() {
        SysUser currentUser = SecurityUtil.getCurrentUser();
        return examRepository.listCompletedByTeacher(currentUser);
    }

    /**
     * 获取试卷
     *
     * @param examId
     * @return
     */
    @Override
    public Exam getExam(Long examId) {
        Exam exam = examRepository.getOne(examId);
        //封装detail
        List<ExamDetail> detailList = examDetailRepository.listByExamId(exam.getId());
        exam.setDetailList(detailList);
        return exam;
    }

    /**
     * 阅卷
     *
     * @param formExam
     */
    @Override
    @Transactional
    public void checkExam(Exam formExam) {
        Exam exam = examRepository.getOne(formExam.getId());
        exam.setIsChecked(true);

        List<ExamDetail> examDetailList = new ArrayList<>();
        formExam.getDetailList().forEach(formDetail -> {
            if(!ObjectUtils.isEmpty(formDetail.getCorrect())){
                ExamDetail detail = examDetailRepository.getOne(formDetail.getId());
                detail.setCorrect(formDetail.getCorrect());
                examDetailList.add(detail);
            }
        });
        examDetailRepository.saveAll(examDetailList);
        //计分
        exam.setGrade(examScoring(exam.getId()));
        examRepository.save(exam);
    }


    /**
     * 验证某试卷，当前学生是否考过
     *
     * @param examPaperId
     * @return
     */
    @Override
    public String validExam(Long examPaperId) {
        SysUser student = SecurityUtil.getCurrentUser();
        ExamPaper examPaper = examPaperRepository.getOne(examPaperId);
        List<Exam> examList = examRepository.listCompletedByPaperAndStudent(examPaper,student);
        if(examList.size()==0){
            return "ok";
        }else{
            return "no";
        }
    }

    /**
     * 考试计分
     * @param examId
     * @return
     */
    private Double examScoring(Long examId){
        AtomicReference<Double> score = new AtomicReference<>(0D);
        List<ExamDetail> examDetailList = examDetailRepository.listByExamId(examId);
        examDetailList.forEach(detail->{
            if(!ObjectUtils.isEmpty(detail.getCorrect())){
                if(detail.getCorrect()){
                    score.updateAndGet(v -> v + detail.getQuestion().getScore());
                }
            }
        });
        return score.get();
    }
}
