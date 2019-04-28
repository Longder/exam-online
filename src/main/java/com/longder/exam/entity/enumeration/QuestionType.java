package com.longder.exam.entity.enumeration;

/**
 * 题目类型枚举
 * Created by Longder
 */
public enum  QuestionType {
    CHOICE("CHOICE","","选择题"),
    FILL("FILL","","填空题"),
    ASK("ASK","","问答题"),
    ESSAY("ESSAY","","简答题");


    private String name;

    private String label;

    private String displayName;


    QuestionType(String name, String label, String displayName) {
        this.name = name;
        this.label = label;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
