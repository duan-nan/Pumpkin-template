package com.pumpkin.disease.entity.website;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Pumpkin
 * @version 1.0
 * @description 前台配置实体类
 * @date 2023/2/21
 */
@Data
@AllArgsConstructor
public class FormatConfig {


    /**
     * Logo
     */
    @ApiModelProperty(value = "网站前台图标", name = "logo")
    private String logo;

    /**
     * 网站前台名称
     */
    @ApiModelProperty(value = "网站前台名称", name = "websiteName")
    private String websiteName;

    /**
     * 网站墓志铭
     */
    @ApiModelProperty(value = "网站墓志铭", name = "motto")
    private String motto;

    /**
     * 作者名称
     */
    @ApiModelProperty(value = "作者名称", name = "author")
    private String author;

    /**
     * 网站公告信息
     */
    @ApiModelProperty(value = "网站公告信息", name = "announcement")
    private String announcement;

    /**
     * 评论背景
     */
    @ApiModelProperty(value = "网站公告信息", name = "commentBack")
    private String commentBack;

    /**
     * 网站创建时间
     */
    @ApiModelProperty(value = "网站创建时间", name = "createDate")
    private String createDate;

    /**
     * 网站版权信息
     */
    @ApiModelProperty(value = "网站版权信息", name = "copyright")
    private String copyright;

    /**
     * 页脚美文
     */
    @ApiModelProperty(value = "页脚美文", name = "footerWord")
    private String footerWord;

    /**
     * 网站备案号
     */
    @ApiModelProperty(value = "网站备案号", name = "caseNumber")
    private String caseNumber;

    /**
     * 网站登陆方式
     */
    @ApiModelProperty(value = "网站登陆方式", name = "formatLoginType")
    private List<String> formatLoginType;

}
