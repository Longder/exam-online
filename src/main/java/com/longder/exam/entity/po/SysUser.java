package com.longder.exam.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * 系统用户实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseIdEntity implements UserDetails {

    /**
     * 姓名
     */
    @Column(name = "name_")
    private String name;
    /**
     * 登录名
     */
    @Column(name = "login_name_")
    private String loginName;

    /**
     * 登陆密码
     */
    @Column(name = "password_")
    private String password;

    /**
     * 用户角色
     */
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "sysUser",cascade = CascadeType.ALL)
    private List<SysUserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
