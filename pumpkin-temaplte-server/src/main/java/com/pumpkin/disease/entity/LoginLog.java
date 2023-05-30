package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 登陆日志表
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@TableName("login_log")
@ApiModel(value = "LoginLog对象", description = "登陆日志表")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("ip地址")
    private String ipAddress;

    @ApiModelProperty("ip来源地址")
    private String ipSource;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("登陆状态（0->失败 1->成功）")
    private Byte loginStatus;

    @ApiModelProperty("响应数据")
    private String responseMessage;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty("逻辑删除")
    private Byte isDeleted;
}
