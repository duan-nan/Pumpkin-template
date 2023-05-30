package com.pumpkin.disease.request.security.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: Pumpkin
 * @description: 密码请求入参
 * @since : 2023/3/13
 */
@Data
public class PasswordRequest {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码为必填项")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码为必填项")
    private String newPassword;

}
