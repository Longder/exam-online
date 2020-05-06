package com.longder.exam.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷生成器对象
 * Created by Longder
 */
@Data
public class PaperGeneratorObject implements Serializable {
    /**
     * 试卷名称
     */
    private String paperName;
    /**
     * 试卷难度系数
     */
    private Integer difficulty;
    /**
     * 考试时间（小时）
     */
    private Integer hours;
    /**
     * 考试时间（分钟）
     */
    private Integer minutes;
    /**
     * 选择题数量
     */
    private Integer choiceCount = 0;
    /**
     * 填空题数量
     */
    private Integer fillCount = 0;
    /**
     * 问答题数量
     */
    private Integer askCount = 0;
    /**
     * 简答题数量
     */
    private Integer essayCount = 0;
    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 所选题目的id
     */
    private List<Long> questionIds;
}
