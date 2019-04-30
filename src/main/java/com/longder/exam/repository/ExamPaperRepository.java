package com.longder.exam.repository;

import com.longder.exam.entity.po.ExamPaper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Longder
 */
public interface ExamPaperRepository extends JpaRepository<ExamPaper,Long> {
}
