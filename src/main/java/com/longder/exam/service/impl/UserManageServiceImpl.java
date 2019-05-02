package com.longder.exam.service.impl;

import com.longder.exam.entity.po.SysRole;
import com.longder.exam.entity.po.SysUser;
import com.longder.exam.entity.po.SysUserRole;
import com.longder.exam.repository.SysUserRepository;
import com.longder.exam.repository.SysUserRoleRepository;
import com.longder.exam.service.UserManageService;
import com.longder.exam.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManageServiceImpl implements UserManageService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;
    /**
     * 默认密码
     */
    @Value("${system.default-password}")
    private String defaultPassword;

    @Override
    public List<SysUser> listStudent() {
        List<SysUser> allUsers = sysUserRepository.findAll();
        return allUsers.stream()
                .filter(sysUser -> sysUser.getRoles().get(0).getRole().equals(SysRole.ROLE_STUDENT))
                .collect(Collectors.toList());
    }

    public List<SysUser> listTeacher(){
        List<SysUser> allUsers = sysUserRepository.findAll();
        return allUsers.stream()
                .filter(sysUser -> sysUser.getRoles().get(0).getRole().equals(SysRole.ROLE_TEACHER))
                .collect(Collectors.toList());
    }
    /**
     * 存储用户
     *
     * @param sysUser 用户实体
     * @param sysRole 用户角色
     */
    @Override
    @Transactional
    public void saveUser(SysUser sysUser, SysRole sysRole) {
        sysUser.setPassword(EncryptionUtil.encrypt(defaultPassword));
        sysUserRepository.save(sysUser);
        SysUserRole userRole = new SysUserRole();
        userRole.setSysUser(sysUser);
        userRole.setRole(sysRole);
        sysUserRoleRepository.save(userRole);
    }
}
