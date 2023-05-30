package com.pumpkin.disease.entity.website;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Pumpkin
 * @version 1.0
 * @description 社交信息实体类
 * @date 2023/2/21
 */
@Data
@AllArgsConstructor
public class SocialConfig {

    /**
     * QQ账号
     */
    private String qqNumber;

    /**
     * 是否展示QQ
     */
    private Boolean showQq;

    /**
     * 微信号
     */
    private String weChatNumber;

    /**
     * 是否展示微信
     */
    private Boolean showWeChat;

    /**
     * 码云地址
     */
    private String giteeUrl;

    /**
     * 是否展示码云
     */
    private Boolean showGitee;

    /**
     * Github地址
     */
    private String githubUrl;

    /**
     * 是否展示Github
     */
    private Boolean showGithub;

    /**
     * B站地址
     */
    private String bilibiliUrl;

    /**
     * 是否展示B站
     */
    private Boolean showBilibili;

    /**
     * CSDN地址
     */
    private String csdnUrl;

    /**
     * 是否展示CSDN
     */
    private Boolean showCsdn;

    /**
     * QQ群号
     */
    private String groupNumber;

    /**
     * 是否展示QQ群
     */
    private Boolean showGroup;
}
