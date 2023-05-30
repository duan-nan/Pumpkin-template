package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色-菜单 半选关联表
 * </p>
 *
 * @author Pumpkin
 * @since 2023-05-29 15:17:18
 */
@Getter
@Setter
@TableName("role_half_menu")
@ApiModel(value = "RoleHalfMenu对象", description = "角色-菜单 半选关联表")
public class RoleHalfMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("半选菜单id")
    private Long halfMenuId;
}
