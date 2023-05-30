package com.pumpkin.disease.response.security.menu;

import lombok.Data;

/**
 * @author: Pumpkin
 * @description: 菜单详情信息
 * @since : 2023/3/9
 */
@Data
public class MenuDetailResponse {

    /**
     * 菜单主键id
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单路由
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单类型（菜单 M   目录 C  按钮 B）
     */
    private String menuType;

    /**
     * 菜单权限
     */
    private String perm;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 父级菜单名称
     */
    private String parentName;

    /**
     * 菜单排序
     */
    private Integer orderNum;

    /**
     * 是否隐藏（0 不隐藏   1 隐藏）
     */
    private Boolean isHidden;

    /**
     * 注释信息
     */
    private String remark;
}
