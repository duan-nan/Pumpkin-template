package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@TableName("user_auth")
@ApiModel(value = "UserAuth对象", description = "用户权限表")
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户主键")
      private String id;

    @ApiModelProperty("用户名/OpenId")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户信息id")
    private Long userInfoId;

    @ApiModelProperty("用户角色id")
    private String userRoleId;

    @ApiModelProperty("用户登录方式（1 用户名  2 邮箱  3 QQ  4 微信）")
    private Integer loginType;

    @ApiModelProperty("是否开启邮箱登录（0 否  1是）")
    private Boolean emailLogin;

    @ApiModelProperty("是否禁用（0 正常  1禁用）")
    private Boolean isDisabled;

    @ApiModelProperty("逻辑删除（0 正常  1 禁用）")
    private Boolean isDeleted;
}
