package com.longder.exam.service.impl;

import com.longder.exam.entity.dto.PaperGeneratorObject;
import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.ExamPaper;
import com.longder.exam.entity.po.ExamPaperQuestion;
import com.longder.exam.entity.po.Question;
import com.longder.exam.repository.CourseRepository;
import com.longder.exam.repository.ExamPaperQuestionRepository;
import com.longder.exam.repository.ExamPaperRepository;
import com.longder.exam.repository.QuestionRepository;
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

    /**
     * 试卷列表
     *
     * @return
     */
    @Override
    public List<ExamPaper> listExamPaper() {
        return examPaperRepository.findAll();
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
        examPaper.setCourse(course);
        examPaper.setName(generatorObject.getPaperName());
        examPaper.setHours(generatorObject.getHours());
        examPaper.setMinutes(generatorObject.getMinutes());
        examPaperRepository.save(examPaper);
        return examPaper;
    }
}
