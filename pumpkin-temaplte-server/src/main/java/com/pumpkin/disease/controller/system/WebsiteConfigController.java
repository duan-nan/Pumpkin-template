package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.annotation.AnonymousAccess;
import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.system.website.WebsiteConfigRequest;
import com.pumpkin.disease.service.WebsiteConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.pumpkin.disease.constant.SystemConstant.LogType.UPDATE_OPERATE;

/**
 * <p>
 * 网站配制表 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@RequiredArgsConstructor
@Api(tags = {"网站配制模块"})
@RequestMapping("/website-config")
public class WebsiteConfigController {

    private final WebsiteConfigService websiteConfigService;


    /**
     * 查询网站配制信息
     *
     * @param websiteModuleCode 网站配制code
     * @return 网站配制信息
     */
    @ApiOperation(value = "[通用]查询网站配制信息")
    @AnonymousAccess
    @GetMapping("/get/{websiteModuleCode}")
    public <T> ResponseResult<T> getWebsiteConfig(@PathVariable Integer websiteModuleCode) {
        return ResponseResult.success("查询网站配制信息成功！", websiteConfigService.getWebsiteConfig(websiteModuleCode));
    }

    /**
     * 更新网站配制信息
     * @param websiteConfigRequest 请求入参
     * @return 网站配制是否更新成功
     */
    @ApiOperation(value = "[后台]更新网站配制信息")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:websiteConfig:update')")
    @PostMapping("/update")
    public ResponseResult<?> updateWebsiteConfig(@RequestBody @Valid WebsiteConfigRequest websiteConfigRequest) {
        websiteConfigService.updateWebsiteConfig(websiteConfigRequest);
        return ResponseResult.success("更新网站配制信息成功！");
    }


}
