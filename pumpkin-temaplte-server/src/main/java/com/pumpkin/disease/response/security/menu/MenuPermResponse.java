package com.pumpkin.disease.response.security.menu;

import lombok.Data;

import java.util.List;

/**
 * @author: Pumpkin
 * @description: 菜单权限列表
 * @since : 2022/12/12
 */
@Data
public class MenuPermResponse {

    /**
     * 菜单id
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限
     */
    private String perm;

    /**
     * 子菜单
     */
    private List<MenuPermResponse> children;
}
