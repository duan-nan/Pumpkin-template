package com.pumpkin.disease.request.security.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: Pumpkin
 * @description: 图片请求入参
 * @since : 2023/3/13
 */
@Data
public class ImageRequest {

    /**
     * 图片请求链接
     */
    @ApiModelProperty(value = "图片请求链接",name = "imageUrl")
    @NotBlank(message = "链接地址不能为空")
    private String imageUrl;

}
