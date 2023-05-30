package com.pumpkin.disease.entity.website;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Pumpkin
 * @version 1.0
 * @description 其他配置实体类
 * @date 2023/2/21
 */
@Data
@AllArgsConstructor
public class OtherConfig {

    /**
     * 路由菜单类型
     */
    @ApiModelProperty(value = "路由菜单类型", name = "menuRouteType")
    private Integer menuRouteType;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", name = "userAvatar")
    private String userAvatar;

    /**
     * 游客头像
     */
    @ApiModelProperty(value = "游客头像", name = "visitorAvatar")
    private String visitorAvatar;

    /**
     * 文件上传类型
     */
    @ApiModelProperty(value = "上传类型", name = "uploadType")
    private String uploadType;

    /**
     * 微信收款码
     */
    @ApiModelProperty(value = "微信收款码", name = "weChatPay")
    private String weChatPay;

    /**
     * 支付宝收款码
     */
    @ApiModelProperty(value = "支付宝收款码", name = "alipayPay")
    private String alipayPay;

    /**
     * WebSocket地址
     */
    @ApiModelProperty(value = "WebSocket地址", name = "webSocketUrl")
    private String webSocketUrl;

    /**
     * 文章审核
     */
    @ApiModelProperty(value = "文章审核", name = "articleVerify")
    private Integer articleVerify;

    /**
     * 留言审核
     */
    @ApiModelProperty(value = "留言审核", name = "leaveVerify")
    private Integer leaveVerify;

    /**
     * 评论审核
     */
    @ApiModelProperty(value = "评论审核", name = "commentVerify")
    private Integer commentVerify;

    /**
     * 邮箱通知
     */
    @ApiModelProperty(value = "邮箱通知", name = "emailNotify")
    private Integer emailNotify;

    /**
     * 聊天室状态
     */
    @ApiModelProperty(value = "聊天室状态", name = "chatRoomStatus")
    private Integer chatRoomStatus;

    /**
     * 播放器状态
     */
    @ApiModelProperty(value = "播放器状态", name = "musicStatus")
    private Integer musicStatus;
}
