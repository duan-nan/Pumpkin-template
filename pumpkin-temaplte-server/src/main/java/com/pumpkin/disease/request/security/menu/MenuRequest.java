package com.pumpkin.disease.request.security.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;


/**
 * @author: Pumpkin
 * @description: 菜单请求入参
 * @since : 2022/12/8
 */
@Data
public class MenuRequest {

    /**
     * 菜单主键id
     */
    @ApiModelProperty(value = "菜单主键id", name = "id")
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空！")
    @ApiModelProperty(value = "菜单名称", name = "name")
    private String name;

    /**
     * 菜单路由
     */
    @ApiModelProperty(value = "菜单路由", name = "path", example = "#")
    private String path;

    /**
     * 菜单组件
     */
    @ApiModelProperty(value = "菜单组件", name = "component", example = "#")
    private String component;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标", name = "icon", example = "#")
    private String icon;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型（菜单 M   目录 C  按钮 B）", name = "menuType")
    private String menuType;

    /**
     * 菜单权限
     */
    @ApiModelProperty(value = "菜单权限", name = "perm", example = "#")
    private String perm;

    /**
     * 父菜单id
     */
    @ApiModelProperty(value = "父菜单id", name = "parentId", example = "0")
    private Long parentId;

    /**
     * 菜单排序
     */
    @Range(min = 1, max = 999,message = "排序值在1-100之间")
    @ApiModelProperty(value = "菜单排序", name = "orderNum", example = "1")
    private Integer orderNum;

    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏（0 不隐藏   1 隐藏）", name = "isHidden", example = "false")
    private Boolean isHidden;

    /**
     * 注释信息
     */
    @ApiModelProperty(value = "注释信息", name = "remark")
    private String remark;

}
