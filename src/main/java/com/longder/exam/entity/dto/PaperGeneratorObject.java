package com.longder.exam.entity.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("paperName", paperName)
                .append("choiceCount", choiceCount)
                .append("fillCount", fillCount)
                .append("askCount", askCount)
                .append("essayCount", essayCount)
                .append("courseId", courseId)
                .toString();
    }
}
