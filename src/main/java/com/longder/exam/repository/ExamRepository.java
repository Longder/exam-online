package com.longder.exam.repository;

import com.longder.exam.entity.po.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Longder
 */
public interface ExamRepository extends JpaRepository<Exam,Long> {
}
