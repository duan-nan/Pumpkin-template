package com.pumpkin.disease.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import com.pumpkin.disease.entity.WebsiteConfig;
import com.pumpkin.disease.enums.website.WebsiteConfigEnum;
import com.pumpkin.disease.mapper.WebsiteConfigMapper;
import com.pumpkin.disease.request.system.website.WebsiteConfigRequest;
import com.pumpkin.disease.service.WebsiteConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.utils.redis.RedisCache;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.MISSING_NECESSARY_PARAMETER;
import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.QUERY_RECORD_NOT_EXISTED;
import static com.pumpkin.disease.constant.RedisPrefixConstant.WebsiteConfigPrefix.DEFAULT_PREFIX;

/**
 * <p>
 * 网站配制表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
@RequiredArgsConstructor
public class WebsiteConfigServiceImpl extends ServiceImpl<WebsiteConfigMapper, WebsiteConfig> implements WebsiteConfigService {

    private final RedisCache redisCache;

    @Override
    public <T> T getWebsiteConfig(Integer websiteModuleCode) {
        //region 检查缓存是否存在
        T t = redisCache.getCacheObject(DEFAULT_PREFIX + websiteModuleCode);
        //endregion

        if (AssertUtil.isNull(t)) {
            WebsiteConfig websiteConfig = baseMapper.selectOne(new LambdaQueryWrapper<WebsiteConfig>().eq(WebsiteConfig::getWebsiteModule, websiteModuleCode));
            AssertUtil.assertIsNotPass(AssertUtil.isNull(websiteConfig), QUERY_RECORD_NOT_EXISTED);
            JSONObject jsonObject = JSON.parseObject(websiteConfig.getWebsiteConfig());
            t = (T) JSON.toJavaObject(jsonObject, WebsiteConfigEnum.getWebsiteConfigByCode(websiteModuleCode).getAClass());


            //region 添加缓存
            redisCache.setCacheObject(DEFAULT_PREFIX + websiteModuleCode, t);
            //endregion
        }

        return t;
    }

    @Override
    public void updateWebsiteConfig(WebsiteConfigRequest websiteConfigRequest) {
        //region 必要参数校验
        if (null == websiteConfigRequest.getWebsiteModuleCode()) {
            throw new BaseException(MISSING_NECESSARY_PARAMETER);
        }
        //endregion

        //region 检测枚举值是否存在
        WebsiteConfigEnum.getWebsiteConfigByCode(websiteConfigRequest.getWebsiteModuleCode());
        //endregion

        //region 数据封装
        WebsiteConfig websiteConfig = baseMapper.selectOne(new LambdaQueryWrapper<WebsiteConfig>().eq(WebsiteConfig::getWebsiteModule, websiteConfigRequest.getWebsiteModuleCode()));
        websiteConfig.setWebsiteConfig(websiteConfigRequest.getConfigJsonData());
        //endregion

        if (baseMapper.updateById(websiteConfig) == 0) {
            throw new BaseException(BusinessErrorConstant.SimpleError.UPDATE_DATA_FAIL);
        }

        //region 清空Redis缓存
        redisCache.deleteObject(DEFAULT_PREFIX + websiteConfigRequest.getWebsiteModuleCode());
        //endregion
    }


}
