package com.pumpkin.disease.response.system.website;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Pumpkin
 * @description: 对象存储基础信息
 * @since : 2023/3/6
 */
@Data
public class ObjectStoreBasicInfoResponse {

    /**
     * 对象存储名称
     */
    @ApiModelProperty(value = "对象存储名称",name = "name")
    private String name;

    /**
     * 对象存储标识
     */
    @ApiModelProperty(value = "对象存储标识",name = "label")
    private String label;

    /**
     * 对象存储是否可用
     */
    @ApiModelProperty(value = "对象存储是否可用",name = "disabled")
    private Boolean disabled;

}
