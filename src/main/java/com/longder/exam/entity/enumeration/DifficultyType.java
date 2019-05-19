package com.longder.exam.entity.enumeration;

import java.util.Arrays;

/**
 * 难度类型
 * Created by Longder
 */
public enum  DifficultyType {
    LEVEL_1(1,"一级"),
    LEVEL_2(2,"二级"),
    LEVEL_3(3,"三级"),
    LEVEL_4(4,"四级"),
    LEVEL_5(5,"五级"),
    LEVEL_6(6,"六级"),
    LEVEL_7(7,"七级"),
    LEVEL_8(8,"八级"),
    LEVEL_9(9,"九级"),
    LEVEL_10(10,"十级");

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

    public static DifficultyType fromName(String name){
        return Arrays.stream(DifficultyType.values()).filter(type-> type.getDisplayName().equals(name)).findFirst().orElse(null);
    }
}
