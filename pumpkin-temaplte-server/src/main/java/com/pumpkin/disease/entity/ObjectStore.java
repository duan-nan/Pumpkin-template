package com.pumpkin.disease.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 对象存储表
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Getter
@Setter
@TableName("object_store")
@ApiModel(value = "ObjectStore对象", description = "对象存储表")
public class ObjectStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("对象存储名称")
    private String objectStoreName;

    @ApiModelProperty("对象存储标识")
    private String objectStoreLabel;

    @ApiModelProperty("若为空，则使用默认值")
    private String domainUrl;

    @ApiModelProperty("accessKey  或 username")
    private String accessKey;

    @ApiModelProperty("密钥")
    private String accessKeySecret;

    @ApiModelProperty("地域节点 / 服务ip")
    private String endpoint;

    @ApiModelProperty("存储桶名称 /  服务器路径")
    private String bucket;

    @ApiModelProperty("信息是否完善")
    private Integer isPerfect;

    @ApiModelProperty("是否通过上传测试")
    private Integer isPassTest;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDeleted;

}
