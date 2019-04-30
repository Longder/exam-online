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
        ExamPaper examPaper = new ExamPaper();
        Course course = courseRepository.getOne(generatorObject.getCourseId());
        examPaper.setCourse(course);
        examPaper.setName(generatorObject.getPaperName());
        examPaperRepository.save(examPaper);
        List<Question> allQuestions = new ArrayList<>();
        //选择题
        if(generatorObject.getChoiceCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.CHOICE,generatorObject.getChoiceCount(),course));
        }
        //填空题
        if(generatorObject.getFillCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.FILL,generatorObject.getFillCount(),course));
        }
        //问答题
        if(generatorObject.getAskCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.ASK,generatorObject.getAskCount(),course));
        }
        //简答题
        if(generatorObject.getEssayCount()>0){
            allQuestions.addAll(randomQuestion(QuestionType.ESSAY,generatorObject.getEssayCount(),course));
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
     * @param questionType
     * @param count
     * @return
     */
    private List<Question> randomQuestion(QuestionType questionType,Integer count,Course course){
        List<Question> questionList = questionRepository.listByTypeAndCourse(questionType,course);
        Collections.shuffle(questionList);
        return questionList.subList(0,count);
    }
}
