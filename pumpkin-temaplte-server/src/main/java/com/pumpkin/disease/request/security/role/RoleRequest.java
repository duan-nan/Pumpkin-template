package com.pumpkin.disease.request.security.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * @author: Pumpkin
 * @description: 角色请求入参
 * @since : 2022/12/9
 */
@Data
public class RoleRequest {

    /**
     * 角色主键id
     */
    @ApiModelProperty(value = "角色主键id", name = "id")
    private String id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空！")
    @ApiModelProperty(value = "角色名称", name = "roleName")
    private String roleName;

    /**
     * 角色标识
     */
    @NotBlank(message = "角色标识不能为空！")
    @ApiModelProperty(value = "角色标识", name = "roleLabel")
    private String roleLabel;

    /**
     * 角色秘钥
     */
    @ApiModelProperty(value = "角色秘钥", name = "roleSecret")
    private String roleSecret;

    /**
     * 角色排序
     */
    @Range(min = 1,max = 99,message = "排序值仅能在1-99之间")
    @ApiModelProperty(value = "排序", name = "orderNum")
    private Integer orderNum;

    /**
     * 是否禁用
     */
    @ApiModelProperty(value = "是否禁用 (0 正常  1禁用)", name = "isDisabled",example = "false")
    private Boolean isDisabled;

}
