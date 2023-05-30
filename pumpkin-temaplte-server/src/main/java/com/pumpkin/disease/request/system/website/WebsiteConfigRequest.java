package com.pumpkin.disease.request.system.website;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: MuZiNan
 * @description: 网站配制请求入参
 * @since : 2023/2/22
 */
@Data
public class WebsiteConfigRequest {

    /**
     * 配制Json数据
     */
    @NotBlank(message = "配制信息不能为空！")
    @ApiModelProperty(value = "配制Json数据", name = "configJsonData", required = true)
    private String configJsonData;

    /**
     * 配制模块
     */
    @ApiModelProperty(value = "配制模块", name = "websiteModuleCode", required = true)
    private Integer websiteModuleCode;


}
