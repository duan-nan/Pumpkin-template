package com.pumpkin.disease.request.system.website;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: MuZiNan
 * @description:
 * @since : 2023/3/3
 */
@Data
public class ObjectStoreStatusRequest {

    /**
     * id
     */
    @ApiModelProperty(value = "id",name = "id")
    private Long id;

    /**
     * 是否通过上传测试
     */
    @ApiModelProperty(value = "是否通过上传测试",name = "isPassTest")
    private Integer isPassTest;
}
