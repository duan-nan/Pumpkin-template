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
 * 角色菜单关联表
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@TableName("role_menu")
@ApiModel(value = "RoleMenu对象", description = "角色菜单关联表")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("菜单id")
    private Long menuId;
}
