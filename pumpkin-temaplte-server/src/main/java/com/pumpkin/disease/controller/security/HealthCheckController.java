package com.pumpkin.disease.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Pumpkin
 * @description: 健康检测接口
 * @since : 2023/1/31
 */
@Slf4j
@RestController
@Api(tags = {"健康检测接口"})
@RequestMapping("/health")
public class HealthCheckController {

    /**
     * 健康检测接口
     * @return ok
     */
    @ApiOperation(value = "健康检测")
    @GetMapping("/check")
    public String check() {
        log.info("health check request!");
        return "ok";
    }
}
