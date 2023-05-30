package com.pumpkin.disease.response.security.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Pumpkin
 * @description: 用户返回信息
 * @since : 2023/3/14
 */
@Data
public class UserResponse {

    /**
     * 用户权限id
     */
    private String id;

    /**
     * 用户名/OpenId
     */
    private String username;

    /**
     * 用户名
     */
    private String nickname;

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
    private Integer deviceType;

    /**
     * 注册来源（1 PC网页、2 小程序、3 app）
     */
    private Integer registeredSource;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源地
     */
    private String ipSource;

    /**
     * 用户登录方式（1 用户名  2 邮箱  3 QQ  4 微信）
     */
    private Integer loginType;

    /**
     * 是否禁用（0 正常  1禁用）
     */
    private Integer isDisabled;

    /**
     * 用户创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    /**
     * 用户最近一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 用户角色Id
     */
    private String roleId;

    /**
     * 用户角色名称
     */
    private String roleName;
}
