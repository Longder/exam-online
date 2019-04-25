package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "COURSE")
public class Course extends BaseIdEntity{
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程描述
     */
    private String description;
}
