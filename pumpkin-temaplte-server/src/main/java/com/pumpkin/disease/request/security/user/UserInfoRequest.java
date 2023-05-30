package com.pumpkin.disease.request.security.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: Pumpkin
 * @description: 用户信息请求入参
 * @since : 2023/3/13
 */
@Data
public class UserInfoRequest {

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空！")
    private String nickname;

    /**
     * 用户性别（-1 未知  0 仙女  1帅哥）
     */
    private Integer gender;

    /**
     * 用户邮箱
     */
    @NotBlank(message = "用户邮箱不能为空！")
    private String email;

    /**
     * 用户个人简介
     */
    private String personIntro;



}
