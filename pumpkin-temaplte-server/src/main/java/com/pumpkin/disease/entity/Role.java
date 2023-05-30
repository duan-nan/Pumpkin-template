package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表信息
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@ApiModel(value = "Role对象", description = "角色表信息")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色主键id")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色标识")
    private String roleLabel;

    @ApiModelProperty("角色秘钥")
    private String roleSecret;

    private Integer orderNum;

    @ApiModelProperty("是否禁用 (0 正常  1禁用)")
    private Boolean isDisabled;

    @ApiModelProperty("逻辑删除 （0 正常  1 删除）")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtUpdate;
}
