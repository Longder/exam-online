package com.longder.exam.repository;

import com.longder.exam.entity.enumeration.QuestionType;
import com.longder.exam.entity.po.Course;
import com.longder.exam.entity.po.Question;
import com.longder.exam.entity.po.SysUser;
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

    /**
     * 通过关键字查询
     * @param keyWord
     * @return
     */
    @Query("SELECT q from Question q where q.content like :keyWord")
    List<Question> liseByKeyWord(@Param("keyWord")String keyWord);

    /**
     * 查询老师所管理的课程的题目
     * @param teacher
     * @return
     */
    @Query("SELECT q from Question q where q.course.teacher = :teacher")
    List<Question> listByTeacher(@Param("teacher") SysUser teacher);

    /**
     * 查询老师所管理的课程的题目，包括关键字过滤
     * @param teacher
     * @param keyWord
     * @return
     */
    @Query("SELECT q from Question q where q.course.teacher = :teacher and q.content like :keyWord")
    List<Question> listByTeacherAndKeyWord(@Param("teacher") SysUser teacher,@Param("keyWord")String keyWord);

    /**
     * 查询老师所管理的课程下的错题
     * @param teacher
     * @return
     */
    @Query("SELECT q from Question q where q.course.teacher = :teacher and q.mistakeCount is not null and q.mistakeCount>0 order by q.mistakeCount desc")
    List<Question> listMistakeByTeacher(@Param("teacher")SysUser teacher);

    /**
     * 查询老师所管理的课程下的错题，包括关键字的查询
     * @param teacher
     * @return
     */
    @Query("SELECT q from Question q where q.course.teacher = :teacher and q.content like :keyWord and q.mistakeCount is not null and q.mistakeCount>0 order by q.mistakeCount desc")
    List<Question> listMistakeByTeacherAndKeyWord(@Param("teacher") SysUser teacher,@Param("keyWord")String keyWord);
}
