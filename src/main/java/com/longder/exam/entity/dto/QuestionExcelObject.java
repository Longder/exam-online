package com.longder.exam.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 从excel导入题目的对象
 * Created by Longder
 */
@Data
public class QuestionExcelObject implements Serializable {
    /**
     * 题目内容
     */
    private String content;
    /**
     * 题目类型
     */
    private String type;
    /**
     * 难度
     */
    private String difficulty;
    /**
     * 分值
     */
    private String score;
    /**
     * 标准答案
     */
    private String answer;
}
