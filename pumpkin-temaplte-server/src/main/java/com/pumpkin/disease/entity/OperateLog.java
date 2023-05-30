package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@TableName("operate_log")
@ApiModel(value = "OperateLog对象", description = "操作日志表")
public class OperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("操作url")
    private String operateUrl;

    @ApiModelProperty("操作模块")
    private String operateModule;

    @ApiModelProperty("操作类型")
    private Integer operateType;

    @ApiModelProperty("操作方法全路径")
    private String operateMethod;

    @ApiModelProperty("操作方法描述")
    private String operateDescription;

    @ApiModelProperty("请求类型")
    private String requestType;

    @ApiModelProperty("请求参数")
    private String requestParams;

    @ApiModelProperty("响应数据")
    private String responseData;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户昵称")
    private String username;

    @ApiModelProperty("操作ip地址")
    private String operateIpAddress;

    @ApiModelProperty("操作ip来源位置")
    private String operateIpSource;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtUpdate;

    @ApiModelProperty("逻辑删除")
    private Byte isDeleted;
}
