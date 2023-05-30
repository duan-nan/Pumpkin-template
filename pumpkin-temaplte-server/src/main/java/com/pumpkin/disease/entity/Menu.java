package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单路由")
    private String path;

    @ApiModelProperty("菜单组件")
    private String component;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("菜单类型（菜单 M   目录 C  按钮 B）")
    private String menuType;

    @ApiModelProperty("菜单权限")
    private String perm;

    @ApiModelProperty("父菜单id")
    private Long parentId;

    @ApiModelProperty("菜单排序")
    private Integer orderNum;

    @ApiModelProperty("是否隐藏（0 不隐藏   1 隐藏）")
    private Boolean isHidden;

    @ApiModelProperty("逻辑删除 （0 正常  1 删除）")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("菜单更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtUpdate;

    @ApiModelProperty("注释信息")
    private String remark;

    @TableField(exist = false)
    private List<Menu> children;
}
