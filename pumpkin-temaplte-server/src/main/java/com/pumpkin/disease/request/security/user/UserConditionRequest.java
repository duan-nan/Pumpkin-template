package com.pumpkin.disease.request.security.user;

import com.pumpkin.disease.base.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Pumpkin
 * @description: 用户条件请求入参
 * @since : 2023/3/13
 */
@Getter
@Setter
public class UserConditionRequest extends BaseRequest {


    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别",name = "gender")
    private Integer gender;

    /**
     * 用户角色
     */
    @ApiModelProperty(value = "用户角色",name = "userRole")
    private String roleId;

    /**
     * 登录方式
     */
    @ApiModelProperty(value = "登录方式",name = "loginType")
    private Integer loginType;

    /**
     * 是否禁用
     */
    @ApiModelProperty(value = "是否禁用（0 正常  1禁用）",name = "isDisabled")
    private Integer isDisabled;

    //TODO 设备类型、注册来源 待扩展

}
