package com.pumpkin.disease.response.system.website;

import lombok.Data;

/**
 * @author: Pumpkin
 * @description: 对象存储Response
 * @since : 2023/3/1
 */
@Data
public class ObjectStoreResponse {

    /**
     * id
     */
    private Long id;

    /**
     * 对象存储名称
     */
    private String objectStoreName;

    /**
     * 对象存储标识
     */
    private String objectStoreLabel;

    /**
     * 若为空，则使用默认值
     */
    private String domainUrl;

    /**
     * accessKey  或 username
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String accessKeySecret;

    /**
     * 地域节点 / 服务ip
     */
    private String endpoint;

    /**
     * 存储桶名称 /  服务器路径
     */
    private String bucket;

    /**
     * 信息是否完善
     */
    private Boolean isPerfect;

    /**
     * 是否通过上传测试
     */
    private Boolean isPassTest;

}
