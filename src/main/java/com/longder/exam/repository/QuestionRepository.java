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
     * 根据题目类型和课程查询
     * @param questionType
     * @return
     */
    @Query("SELECT q from Question q where q.type = :questionType and q.course = :course")
    List<Question> listByTypeAndCourse(@Param("questionType") QuestionType questionType,@Param("course") Course course);
}
