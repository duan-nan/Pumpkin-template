package com.pumpkin.disease.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/4
 * @description: Security封装用户实体类
 * @version： 1.0
 */
@Data
public class User implements UserDetails {

    /**
     * 用户信息主键
     */
    private String id;

    /**
     * 用户信息id
     */
    private Long userInfoId;

    /**
     * 用户名/OpenId
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户角色
     */
    private Role role;

    /**
     * 用户登录方式（1 用户名  2 邮箱  3 QQ  4 微信）
     */
    private Integer loginType;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别（-1 未知  0 仙女  1帅哥）
     */
    private Integer gender;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户个人简介
     */
    private String personIntro;

    /**
     * 设备类型（1 PC、2 安卓、3 苹果 ）
     */
    private String deviceType;

    /**
     * 注册来源（1 PC网页、2 小程序、3 app）
     */
    private String registeredSource;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源地
     */
    private String ipSource;

    /**
     * 用户创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 用户信息更新时间
     */
    private LocalDateTime gmtUpdate;

    /**
     * 用户最近一次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 是否开启邮箱登录（0 否  1是）
     */
    private Boolean emailLogin;

    /**
     * 是否禁用（0 正常  1禁用）
     */
    private Boolean isDisabled;

    /**
     * 逻辑删除（0 正常  1 禁用）
     */
    private Boolean isDeleted;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority list = new SimpleGrantedAuthority(role.getRoleLabel());
        return Collections.singleton(list);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
