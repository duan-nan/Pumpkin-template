package com.pumpkin.disease.request.security.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author: Pumpkin
 * @description: 角色权限请求入参
 * @since : 2022/12/12
 */
@Data
public class RolePermRequest {

    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空！")
    @ApiModelProperty(value = "角色id",name = "roleId")
    private String roleId;

    /**
     * 菜单id集合
     */
    @ApiModelProperty(value = "菜单id集合",name = "menuIds")
    private Set<Long> menuIds;

    /**
     * 半选菜单id集合
     */
    @ApiModelProperty(value = "半选菜单id集合",name = "halfCheckedIds")
    private Set<Long> halfCheckedIds;

}
