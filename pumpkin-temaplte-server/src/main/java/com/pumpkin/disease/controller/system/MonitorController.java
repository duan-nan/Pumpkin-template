package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.entity.monitor.SystemHardwareInfo;
import com.pumpkin.disease.response.system.montior.MonitorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Pumpkin
 * @description: 系统监控接口
 * @since : 2023/1/31
 */
@RestController
@Api(tags = {"资源监控模块"})
@RequestMapping("/monitor")
public class MonitorController {

    /**
     * 获取系统资源信息
     *
     * @return {@link MonitorResponse} 资源信息
     * @throws Exception 异常信息
     */
    @ApiOperation(value = "[后台]获取系统资源信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:monitor:list')")
    @GetMapping("/system")
    public ResponseResult<MonitorResponse> getMonitor() throws Exception {
        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        systemHardwareInfo.copyTo();
        MonitorResponse monitorResponse = new MonitorResponse();
        monitorResponse.setJvm(systemHardwareInfo.getJvm());
        monitorResponse.setCpu(systemHardwareInfo.getCpu());
        monitorResponse.setMem(systemHardwareInfo.getMem());
        return ResponseResult.success("获取资源监控信息成功！", monitorResponse);
    }

}
