package com.longder.exam.repository;

import com.longder.exam.entity.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SysUserRepository extends JpaRepository<SysUser,Long> {

    @Query("select s from SysUser s where s.loginName = :loginName")
    SysUser getByLoginName(@Param("loginName") String loginName);
}
