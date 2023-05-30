package com.pumpkin.disease.strategy.context;

import com.pumpkin.disease.entity.website.OtherConfig;
import com.pumpkin.disease.enums.file.UploadModeEnum;
import com.pumpkin.disease.enums.website.WebsiteConfigEnum;
import com.pumpkin.disease.service.WebsiteConfigService;
import com.pumpkin.disease.strategy.UploadStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author: Pumpkin
 * @description: 文件上传策略上下文
 * @since : 2023/2/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadStrategyContext {

    private final Map<String, UploadStrategy> uploadStrategyMap;
    private final WebsiteConfigService websiteConfigService;

    /**
     * 执行上传文件策略
     *
     * @param file       文件
     * @param uploadPath 上传路径
     * @return 资源请求路径
     */
    public String executeUploadFile(MultipartFile file, String uploadPath) {
        //region 获取网站上传配制
        OtherConfig otherConfig = websiteConfigService.getWebsiteConfig(WebsiteConfigEnum.OTHER_MODULE.getWebsiteModuleCode());
        String mode = (otherConfig == null || StringUtils.isEmpty(otherConfig.getUploadType())) ? UploadModeEnum.PROJECT_UPLOAD.getMode() : otherConfig.getUploadType();
        //endregion
        return uploadStrategyMap.get(UploadModeEnum.getEnumByMode(mode).getStrategy()).uploadFile(file, uploadPath);
    }

    /**
     * 执行上传文件策略
     *
     * @param mode       模型
     * @param file       文件
     * @param uploadPath 上传路径
     * @return 资源请求路径
     */
    public String executeUploadFile(MultipartFile file, String mode, String uploadPath) {
        return uploadStrategyMap.get(UploadModeEnum.getEnumByMode(mode).getStrategy()).uploadFile(file, uploadPath);
    }

}
