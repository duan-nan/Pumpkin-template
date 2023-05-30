package com.pumpkin.disease.request.system.website;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: MuZiNan
 * @description: 对象存储请求入参
 * @since : 2023/3/2
 */
@Data
public class ObjectStoreRequest {

    /**
     * id
     */
    @ApiModelProperty(value = "对象存储名称",name = "id")
    private Long id;

    /**
     * 对象存储名称
     */
    @ApiModelProperty(value = "对象存储名称",name = "objectStoreName")
    @NotBlank(message = "对象存储名称不能为空")
    private String objectStoreName;

    /**
     * 对象存储标识
     */
    @ApiModelProperty(value = "对象存储标识",name = "objectStoreLabel")
    @NotBlank(message = "对象存储标识不能为空")
    private String objectStoreLabel;

    /**
     * 前置域名
     */
    @ApiModelProperty(value = "若为空，则使用默认值",name = "domainUrl")
    private String domainUrl;

    /**
     * accessKey  或 username
     */
    @ApiModelProperty(value = "accessKey  或 username",name = "accessKey")
    private String accessKey;

    /**
     * 密钥
     */
    @ApiModelProperty(value = "密钥",name = "accessKeySecret")
    private String accessKeySecret;

    /**
     * 地域节点 / 服务ip
     */
    @ApiModelProperty(value = "地域节点 / 服务ip",name = "endpoint")
    private String endpoint;

    /**
     * 存储桶名称 /  服务器路径
     */
    @ApiModelProperty(value = "存储桶名称 /  服务器路径",name = "bucket")
    private String bucket;

    /**
     * 信息是否完善
     */
    @ApiModelProperty(value = "信息是否完善",name = "isPrefect")
    private Integer isPerfect;
}
