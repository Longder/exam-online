package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 课程实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "COURSE")
@Proxy(lazy = false)
public class Course extends BaseIdEntity{
    /**
     * 课程名称
     */
    @Column(name = "name_")
    private String name;
    /**
     * 课程描述
     */
    @Column(name = "description_")
    private String description;
    /**
     * 该课程的老师
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id_")
    private SysUser teacher;
}
