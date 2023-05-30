package com.pumpkin.disease.entity.website;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Pumpkin
 * @version 1.0
 * @description 后台配置实体类
 * @date 2023/2/21
 */
@Data
@AllArgsConstructor
public class BackstageConfig {

    /**
     * 网站前台图标
     */
    @ApiModelProperty(value = "网站后台标题", name = "websiteTitle")
    private String websiteTitle;

    /**
     * 网站前台名称
     */
    @ApiModelProperty(value = "网站后台名称", name = "websiteName")
    private String websiteName;

    /**
     * 作者名称
     */
    @ApiModelProperty(value = "网站标语", name = "websitePoster")
    private String websitePoster;

    /**
     * 网站版权信息
     */
    @ApiModelProperty(value = "网站版权信息", name = "copyright")
    private String copyright;

    /**
     * 网站图标
     */
    @ApiModelProperty(value = "网站图标", name = "favicon")
    private String favicon;

    /**
     * 网站Logo
     */
    @ApiModelProperty(value = "网站Logo", name = "logo")
    private String logo;

    /**
     * 网站背景图片
     */
    @ApiModelProperty(value = "网站背景图片", name = "background")
    private String background;

    /**
     * 网站登陆方式
     */
    @ApiModelProperty(value = "网站登陆方式", name = "backstageLoginType")
    private List<String> backstageLoginType;
}
