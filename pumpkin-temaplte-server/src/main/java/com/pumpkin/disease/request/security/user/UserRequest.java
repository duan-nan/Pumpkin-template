package com.pumpkin.disease.request.security.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author: Pumpkin
 * @description: 用户请求入参
 * @since : 2023/3/15 21:49
 */
@Data
public class UserRequest {

    /**
     * id
     */
    @ApiModelProperty(value = "用户主键", name = "id")
    private String id;

    /**
     * 用户名/OpenId
     */
    @NotBlank(message = "用户名不能为空！")
    @ApiModelProperty(value = "用户名/OpenId", name = "username")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空！")
    @ApiModelProperty(value = "用户密码", name = "password")
    private String password;

    /**
     * 用户角色id
     */
    @NotBlank(message = "用户角色为必选项！")
    @ApiModelProperty(value = "用户角色id", name = "roleId")
    private String roleId;

    /**
     * 是否禁用
     */
    @PositiveOrZero(message = "用户状态枚举值错误！")
    @ApiModelProperty(value = "是否禁用（0 正常  1禁用）", name = "isDisabled")
    private Integer isDisabled;

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为为空！")
    @ApiModelProperty(value = "用户昵称", name = "nickname")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", name = "avatar")
    private String avatar;

    /**
     * 用户性别
     */
    @Range(min = -1, max = 1, message = "用户性别枚举值错误！")
    @ApiModelProperty(value = "用户性别（-1 未知  0 仙女  1帅哥）", name = "gender")
    private Integer gender;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式错误！")
    @ApiModelProperty(value = "用户邮箱", name = "email")
    private String email;

    /**
     * 用户个人简介
     */
    @ApiModelProperty(value = "用户个人简介", name = "personIntro")
    private String personIntro;

    /**
     * 设备类型
     */
    @Positive(message = "设备类型枚举值错误！")
    @ApiModelProperty(value = "设备类型（1 PC、2 安卓、3 苹果 ）", name = "deviceType")
    private Integer deviceType;

    /**
     * 注册来源
     */
    @Positive(message = "注册来源枚举值错误！")
    @ApiModelProperty(value = "注册来源（1 PC网页、2 小程序、3 app）", name = "registeredSource")
    private Integer registeredSource;

    /**
     * 登陆类型
     */
    @Positive(message = "用户登录方式枚举值错误！")
    @ApiModelProperty(value = "用户登录方式（1 用户名  2 邮箱  3 QQ  4 微信）", name = "loginType")
    private Integer loginType;

    /**
     * 是否开启邮箱登录（0 否  1是）
     */
    @PositiveOrZero(message = "是否开启邮箱登录枚举值错误！")
    @ApiModelProperty(value = "是否开启邮箱登录（0 否  1是）", name = "emailLogin")
    private Integer emailLogin;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址",name = "ipAddress")
    private String ipAddress;

    /**
     * ip来源
     */
    @ApiModelProperty(value = "ip来源地",name = "ipSource")
    private String ipSource;
}
