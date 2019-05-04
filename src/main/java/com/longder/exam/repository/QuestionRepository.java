package com.longder.exam.repository;

import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Longder
 */
public interface QuestionRepository extends JpaRepository<Question,Long> {

    /**
     * 根据题目类型和课程和题目难度查询
     * @param questionType
     * @return
     */
    @Query("SELECT q from Question q where q.type = :questionType and q.course = :course and q.difficulty = :difficulty")
    List<Question> listByTypeAndCourseAndDifficulty(@Param("questionType") QuestionType questionType,
                                                    @Param("course") Course course,
                                                    @Param("difficulty")Integer difficulty);

    /**
     * 根据课程找题目
     * @param courseId
     * @return
     */
    @Query("SELECT q from Question q where q.course.id = :courseId")
    List<Question> listByCourseId(@Param("courseId") Long courseId);
}
