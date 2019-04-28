package com.longder.exam.entity.enumeration;

/**
 * 难度类型
 * Created by Longder
 */
public enum  DifficultyType {
    LEVEL_1(1,"一级"),
    LEVEL_2(2,"二级"),
    LEVEL_3(3,"三级");

    private int value;
    private String displayName;

    DifficultyType(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
