package com.pumpkin.disease.request.security.email;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author: Pumpkin
 * @description: 邮箱请求入参
 * @since : 2023/4/23 13:36
 */
@Data
@AllArgsConstructor
public class EmailRequest {

    /**
     * 接受者邮箱号
     */
    @NotBlank(message = "接受者邮箱不能为空！")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(?:\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$",message = "邮箱不合法！")
    @ApiModelProperty(value = "接受者邮箱号", name = "receiverEmail", required = true, example = "486356410@qq.com")
    private String receiverEmail;

    /**
     * 邮箱验证码类型
     */
    @NotNull(message = "验证码类型不能为空！")
    @ApiModelProperty(value = "邮箱验证码类型", name = "emailCaptchaType", required = true, example = "1")
    private Integer emailCaptchaCode;
}