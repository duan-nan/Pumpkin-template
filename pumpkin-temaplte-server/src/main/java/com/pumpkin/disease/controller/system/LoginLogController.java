package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.system.log.LoginLogRequest;
import com.pumpkin.disease.response.system.log.LoginLogResponse;
import com.pumpkin.disease.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.pumpkin.disease.constant.SystemConstant.LogType.*;

/**
 * <p>
 * 登陆日志表 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@Api(tags = "登陆日志模块")
@RequiredArgsConstructor
@RequestMapping("/login-log")
public class LoginLogController {

    private final LoginLogService loginLogService;

    /**
     * 查询登录日志信息列表
     * @param loginLogRequest 登陆日志请求入参类
     * @return {@link LoginLogResponse} 日志信息
     */
    @ApiOperation(value = "[后台]查询登录日志信息列表")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:loginlog:query')")
    @PostMapping("/list")
    public ResponseResult<PageResult<LoginLogResponse>> listLoginLog(@RequestBody @Valid LoginLogRequest loginLogRequest) {
        return ResponseResult.success("查询成功", loginLogService.listLoginLog(loginLogRequest));
    }

    /**
     * 批量删除日志信息
     * @param idRequest id集合
     * @return 是否删除成功
     */
    @ApiOperation(value = "[后台]批量删除日志信息")
    @NormalOperateLog(operateType = DELETE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:loginlog:delete')")
    @DeleteMapping("/batchDelete")
    public ResponseResult<?> batchDeleteLoginLog(@RequestBody IDRequest<Long> idRequest){
        loginLogService.batchDeleteLoginLog(idRequest);
        return ResponseResult.success("删除成功");
    }

}
