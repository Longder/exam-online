package com.longder.exam.repository;

import com.longder.exam.BaseTest;
import com.longder.exam.entity.po.SysRole;
import com.longder.exam.entity.po.SysUser;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class SysUserRepositoryTest extends BaseTest {

    @Resource
    private SysUserRepository sysUserRepository;

    @Test
    public void testListByRole(){
        List<SysUser> sysUserList = sysUserRepository.listByRole(SysRole.ROLE_ADMIN);
        Assert.assertTrue(sysUserList.size()>0);
    }
}
