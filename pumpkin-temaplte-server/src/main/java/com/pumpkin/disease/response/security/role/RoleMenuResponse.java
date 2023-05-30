package com.pumpkin.disease.response.security.role;

import lombok.Data;

import java.util.Set;

/**
 * @author: Pumpkin
 * @description: 角色菜单响应信息
 * @since : 2022/12/9
 */
@Data
public class RoleMenuResponse {

    /**
     * 角色主键id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String roleLabel;

    /**
     * 菜单id集合
     */
    private Set<Long> menuIdList;

}
