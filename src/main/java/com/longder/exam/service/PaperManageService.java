package com.longder.exam.service;

import com.longder.exam.entity.dto.PaperGeneratorObject;
import com.longder.exam.entity.po.ExamPaper;

import java.util.List;

/**
 * 试卷管理的Service
 * Created by Longder
 */
public interface PaperManageService {

    /**
     * 试卷列表
     * @return
     */
    List<ExamPaper> listExamPaper();

    /**
     * 生成试卷
     * @param generatorObject
     */
    void generatePaper(PaperGeneratorObject generatorObject);
}
