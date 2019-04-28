package com.longder.exam.entity.dto;

import lombok.Data;

import java.io.Serializable;

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
     * 选择题数量
     */
    private Integer choiceCount;
    /**
     * 填空题数量
     */
    private Integer fillCount;
    /**
     * 问答题数量
     */
    private Integer askCount;
    /**
     * 简答题数量
     */
    private Integer essayCount;
}
