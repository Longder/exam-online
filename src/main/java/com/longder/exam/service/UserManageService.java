package com.longder.exam.service;


import com.longder.exam.entity.po.SysRole;
import com.longder.exam.entity.po.SysUser;

import java.util.List;

public interface UserManageService {
    /**
     * 学生列表
     * @return
     */
    List<SysUser> listStudent();

    /**
     * 教师列表
     * @return
     */
    List<SysUser> listTeacher();

    /**
     * 存储用户
     * @param sysUser 用户实体
     * @param sysRole 用户角色
     */
    void addUser(SysUser sysUser, SysRole sysRole);

    /**
     * 获取一个用户
     * @param userId
     * @return
     */
    SysUser getOneUser(Long userId);

    /**
     * 修改用户
     * @param sysUser
     */
    void updateUser(SysUser sysUser);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Long userId);

}
