package com.pumpkin.disease.service;

import com.pumpkin.disease.entity.WebsiteConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pumpkin.disease.request.system.website.WebsiteConfigRequest;

/**
 * <p>
 * 网站配制表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface WebsiteConfigService extends IService<WebsiteConfig> {

    /**
     * 查询网站配制信息
     * @param websiteModuleCode 网站配制code
     * @return 网站配制信息
     * @param <T> 占位符
     */
    <T> T getWebsiteConfig(Integer websiteModuleCode);

    /**
     * 更新网站配制信息
     * @param websiteConfigRequest 请求入参
     */
    void updateWebsiteConfig(WebsiteConfigRequest websiteConfigRequest);
}
