package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@TableName("user_info")
@ApiModel(value = "UserInfo对象", description = "用户信息表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户信息主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    private String nickname;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户性别（-1 未知  0 仙女  1帅哥）")
    private Integer gender;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户个人简介")
    private String personIntro;

    @ApiModelProperty("设备类型（1 PC、2 安卓、3 苹果 ）")
    private Integer deviceType;

    @ApiModelProperty("注册来源（1 PC网页、2 小程序、3 app）")
    private Integer registeredSource;

    @ApiModelProperty("ip地址")
    private String ipAddress;

    @ApiModelProperty("ip来源地")
    private String ipSource;

    @ApiModelProperty("用户创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("用户信息更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtUpdate;

    @ApiModelProperty("用户最近一次登录时间")
    private LocalDateTime lastLoginTime;
}
