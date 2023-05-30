package com.pumpkin.disease.request.system.website;

import com.pumpkin.disease.base.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: MuZiNan
 * @description: 对象存储请求入参
 * @since : 2023/3/1
 */
@Getter
@Setter
public class ObjectStoreConditionRequest extends BaseRequest {

    /**
     * 对象存储标识
     */
    @ApiModelProperty(value = "对象存储标识",name = "objectStoreLabel")
    private String objectStoreLabel;

    /**
     * 信息是否完善
     */
    @ApiModelProperty(value = "信息是否完善",name = "isPrefect")
    private Integer isPrefect;

    /**
     * 是否通过上传测试
     */
    @ApiModelProperty(value = "是否通过上传测试",name = "isPassTest")
    private Integer isPassTest;

}
