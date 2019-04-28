package com.longder.exam.repository;

import com.longder.exam.entity.po.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Longder
 */
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
