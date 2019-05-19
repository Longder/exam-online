package com.longder.exam.service.impl;

import com.longder.exam.entity.dto.QuestionExcelObject;
import com.longder.exam.entity.enumeration.DifficultyType;
import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.ExamPaperQuestion;
import com.longder.exam.entity.po.Question;
import com.longder.exam.entity.po.SysUser;
import com.longder.exam.repository.CourseRepository;
import com.longder.exam.repository.ExamPaperQuestionRepository;
import com.longder.exam.repository.QuestionRepository;
import com.longder.exam.security.SecurityUtil;
import com.longder.exam.service.QuestionManageService;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目管理的Service
 * Created by Longder
 */
@Service
public class QuestionManageServiceImpl implements QuestionManageService {

    @Resource
    private QuestionRepository questionRepository;
    @Resource
    private CourseRepository courseRepository;
    @Resource
    private ExamPaperQuestionRepository examPaperQuestionRepository;
    @Value("classpath:question-excel-config.xml")
    private org.springframework.core.io.Resource resource;
    /**
     * 错题加难度的阶梯
     */
    @Value("${system.mistake-step}")
    private Integer mistakeStep;

    /**
     * 查询获取一个题目
     *
     * @param questionId
     * @return
     */
    @Override
    public Question getOneQuestion(Long questionId) {
        return questionRepository.getOne(questionId);
    }

    /**
     * 题目列表
     *
     * @return
     */
    @Override
    public List<Question> listQuestion(String keyWord) {
        SysUser teacher = SecurityUtil.getCurrentUser();
        if (ObjectUtils.isEmpty(keyWord)) {
            return questionRepository.listByTeacher(teacher);
        } else {
            keyWord = "%" + keyWord + "%";
            return questionRepository.listByTeacherAndKeyWord(teacher,keyWord);
        }
    }

    /**
     * 错题列表
     *
     * @param keyWord
     * @return
     */
    @Override
    public List<Question> listQuestionForMistake(String keyWord) {
        return null;
    }

    /**
     * 存储题目，新增和修改都走这个
     *
     * @param question
     */
    @Override
    @Transactional
    public void saveQuestion(Question question) {
        //处理非选择题的内容
        if (!QuestionType.CHOICE.equals(question.getType())) {
            question.setChoiceA(null);
            question.setChoiceB(null);
            question.setChoiceC(null);
            question.setChoiceD(null);
        }
        questionRepository.save(question);
    }

    /**
     * 从excel导入题目
     *
     * @param excelFile
     */
    @Override
    @Transactional
    public void importQuestionsFormExcel(Long courseId, File excelFile) {
        XLSReader mainReader;
        final List<QuestionExcelObject> resultList = new ArrayList<>();
        final Map<String, Object> beans = new HashMap<>();
        try {
            InputStream inputStream = new FileInputStream(excelFile);
            mainReader = ReaderBuilder.buildFromXML(resource.getInputStream());
            beans.put("objs", resultList);
            mainReader.read(inputStream, beans);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultList.size() > 0) {
            Course course = courseRepository.getOne(courseId);
            List<Question> questionList = new ArrayList<>();
            resultList.forEach(result -> {
                Question question = new Question();
                question.setContent(result.getContent().replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;"));
                question.setType(QuestionType.fromValue(result.getType()));
                question.setDifficulty(DifficultyType.fromName(result.getDifficulty()).getValue());
                question.setScore(Double.parseDouble(result.getScore()));
                question.setAnswer(result.getAnswer());
                if(QuestionType.CHOICE.equals(question.getType())){
                    question.setChoiceA(result.getChoiceA());
                    question.setChoiceB(result.getChoiceB());
                    question.setChoiceC(result.getChoiceC());
                    question.setChoiceD(result.getChoiceD());
                }
                question.setCourse(course);
                questionList.add(question);
            });
            questionRepository.saveAll(questionList);
        }
    }

    /**
     * 删除一个题目
     *
     * @param questionId
     * @return
     */
    @Override
    @Transactional
    public String deleteOneQuestion(Long questionId) {
        Question question = questionRepository.getOne(questionId);
        List<ExamPaperQuestion> paperQuestionList = examPaperQuestionRepository.listByQuestion(question);
        if (paperQuestionList.size() == 0) {
            questionRepository.deleteById(questionId);
            return "ok";
        } else {
            return "no";
        }
    }

    /**
     * 记录统计错题
     *
     * @param questionId
     */
    @Override
    @Transactional
    public void countMistake(Long questionId) {
        Question question = questionRepository.getOne(questionId);
        if(ObjectUtils.isEmpty(question.getMistakeCount())){//空值转为0
            question.setMistakeCount(0);
        }
        question.setMistakeCount(question.getMistakeCount() + 1);
        //如果错题达到一定程度，就增加难度
        if(question.getMistakeCount().equals(mistakeStep)//5
                ||question.getMistakeCount().equals(mistakeStep*2)
                ||question.getMistakeCount().equals(mistakeStep*3)){//2倍
            Integer diff = question.getDifficulty() + 1;
            if(diff <= 10){
                question.setDifficulty(diff);
            }
        }
        questionRepository.save(question);
    }
}
