package com.longder.exam.entity.vo;

import com.longder.exam.entity.po.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * 学生信息Vo
 */
@Data
public class StudentVo implements Serializable {
    /**
     * 学生实体信息
     */
    private SysUser student;

    public StudentVo(){

    }

    public StudentVo(SysUser student) {
        this.student = student;
    }
}
