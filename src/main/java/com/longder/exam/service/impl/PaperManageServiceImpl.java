package com.longder.exam.service.impl;

import com.longder.exam.entity.dto.PaperGeneratorObject;
import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.*;
import com.longder.exam.repository.*;
import com.longder.exam.security.SecurityUtil;
import com.longder.exam.service.PaperManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 试卷管理
 * Created by Longder
 */
@Service
public class PaperManageServiceImpl implements PaperManageService {
    @Resource
    private CourseRepository courseRepository;
    @Resource
    private QuestionRepository questionRepository;
    @Resource
    private ExamPaperRepository examPaperRepository;
    @Resource
    private ExamPaperQuestionRepository examPaperQuestionRepository;
    @Resource
    private ExamRepository examRepository;

    /**
     * 试卷列表
     *
     * @return
     */
    @Override
    public List<ExamPaper> listExamPaper() {
        return examPaperRepository.listPublished();
    }

    /**
     * 当前教师下的课程的试卷
     *
     * @return
     */
    @Override
    public List<ExamPaper> listExamPaperForCurrentTeacher() {
        SysUser teacher = SecurityUtil.getCurrentUser();
        return examPaperRepository.listByTeacher(teacher);
    }

    /**
     * 生成试卷
     *
     */
    @Override
    @Transactional
    public void generatePaper(PaperGeneratorObject generatorObject) {
        Course course = courseRepository.getOne(generatorObject.getCourseId());
        ExamPaper examPaper = saveExamPaper(generatorObject);
        List<Question> allQuestions = new ArrayList<>();
        //选择题
        if(generatorObject.getChoiceCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.CHOICE,generatorObject.getChoiceCount(),course,generatorObject.getDifficulty()));
        }
        //填空题
        if(generatorObject.getFillCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.FILL,generatorObject.getFillCount(),course,generatorObject.getDifficulty()));
        }
        //问答题
        if(generatorObject.getAskCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.ASK,generatorObject.getAskCount(),course,generatorObject.getDifficulty()));
        }
        //简答题
        if(generatorObject.getEssayCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.ESSAY,generatorObject.getEssayCount(),course,generatorObject.getDifficulty()));
        }
        //关联题目与试卷
        List<ExamPaperQuestion> paperQuestionList = new ArrayList<>();
        allQuestions.forEach(question -> {
            ExamPaperQuestion paperQuestion = new ExamPaperQuestion();
            paperQuestion.setExamPaper(examPaper);
            paperQuestion.setQuestion(question);
            paperQuestionList.add(paperQuestion);
        });
        examPaperQuestionRepository.saveAll(paperQuestionList);
    }

    /**
     * 随机选题
     * @return
     */
    private List<Question> randomQuestion(QuestionType questionType,Integer count,Course course,Integer difficulty){
        List<Question> questionList = questionRepository.listByTypeAndCourseAndDifficulty(questionType,course,difficulty);
        Collections.shuffle(questionList);
        if(questionList.size()<=count){
            return questionList;
        }else{
            return questionList.subList(0,count);
        }
    }

    /**
     * 获取某课程下的所有题目
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Question> listQuestionForCourse(Long courseId) {
        return questionRepository.listByCourseId(courseId);
    }

    /**
     * 手动组卷
     *
     * @param generatorObject
     */
    @Override
    @Transactional
    public void manualPaper(PaperGeneratorObject generatorObject) {
        ExamPaper examPaper = saveExamPaper(generatorObject);
        //关联题目与试卷
        List<ExamPaperQuestion> paperQuestionList = new ArrayList<>();
        generatorObject.getQuestionIds().forEach(questionId->{
            ExamPaperQuestion paperQuestion = new ExamPaperQuestion();
            Question question = questionRepository.getOne(questionId);
            paperQuestion.setExamPaper(examPaper);
            paperQuestion.setQuestion(question);
            paperQuestionList.add(paperQuestion);
        });
        examPaperQuestionRepository.saveAll(paperQuestionList);
    }

    private ExamPaper saveExamPaper(PaperGeneratorObject generatorObject){
        ExamPaper examPaper = new ExamPaper();
        Course course = courseRepository.getOne(generatorObject.getCourseId());
        //默认未发布
        examPaper.setPublished(false);
        examPaper.setCourse(course);
        examPaper.setName(generatorObject.getPaperName());
        examPaper.setHours(generatorObject.getHours());
        examPaper.setMinutes(generatorObject.getMinutes());
        examPaperRepository.save(examPaper);
        return examPaper;
    }

    /**
     * 获取试卷
     *
     * @param paperId
     * @return
     */
    @Override
    public ExamPaper getPaper(Long paperId) {
        ExamPaper examPaper = examPaperRepository.getOne(paperId);
        List<ExamPaperQuestion> examPaperQuestionList = examPaperQuestionRepository.listByExamPaper(examPaper);
        List<Question> questionList = new ArrayList<>();
        examPaperQuestionList.forEach(examPaperQuestion -> {
            questionList.add(examPaperQuestion.getQuestion());
        });
        examPaper.setQuestionList(questionList);
        return examPaper;
    }

    /**
     * 删除试卷
     *
     * @param paperId
     * @return
     */
    @Override
    @Transactional
    public String deleteOnePaper(Long paperId) {
        //查询是否有已经完成的考试
        ExamPaper examPaper = examPaperRepository.getOne(paperId);
        List<Exam> examList = examRepository.listCompletedByPaper(examPaper);
        if(examList.size()==0){
            examPaperRepository.deleteById(paperId);
            return "ok";
        }else{
            return "no";
        }

    }
}
