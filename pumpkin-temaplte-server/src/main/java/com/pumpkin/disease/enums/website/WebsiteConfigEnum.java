package com.pumpkin.disease.enums.website;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import com.pumpkin.disease.entity.website.BackstageConfig;
import com.pumpkin.disease.entity.website.FormatConfig;
import com.pumpkin.disease.entity.website.OtherConfig;
import com.pumpkin.disease.entity.website.SocialConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Pumpkin
 * @description: 网站配制枚举
 * @since : 2023/2/21
 */
@Getter
@AllArgsConstructor
public enum WebsiteConfigEnum {

    /**
     * 前台网站配制模块
     */
    FORMAT_MODULE(1, FormatConfig.class),
    /**
     * 后台网站配制模块
     */
    BACKSTAGE_MODULE(2, BackstageConfig.class),
    /**
     * 社交配制模块
     */
    SOCIAL_MODULE(3, SocialConfig.class),
    /**
     * 其他网站配制模块
     */
    OTHER_MODULE(4, OtherConfig.class);

    /**
     * 网站配制code
     */
    private final Integer websiteModuleCode;

    /**
     * 网站模块
     */
    private final Class aClass;


    /**
     * 初始化map
     */
    private static final Map<Integer, WebsiteConfigEnum> CODE_NAME_MAP = new HashMap<>((int) ((WebsiteConfigEnum.values().length / 0.75) + 1));

    static {
        for (WebsiteConfigEnum websiteConfigEnum : WebsiteConfigEnum.values()) {
            CODE_NAME_MAP.put(websiteConfigEnum.getWebsiteModuleCode(), websiteConfigEnum);
        }
    }

    /**
     * 根据code获取网站配制枚举
     *
     * @param websiteModuleCode code
     * @return {@link WebsiteConfigEnum} 网站配制枚举类
     */
    public static WebsiteConfigEnum getWebsiteConfigByCode(Integer websiteModuleCode) {
        if (CODE_NAME_MAP.containsKey(websiteModuleCode)) {
            return CODE_NAME_MAP.get(websiteModuleCode);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }

}
