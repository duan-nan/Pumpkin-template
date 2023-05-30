package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.entity.OperateLog;
import com.pumpkin.disease.request.system.log.OperateLogRequest;
import com.pumpkin.disease.service.OperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@Api(tags = {"操作日志模块"})
@RequiredArgsConstructor
@RequestMapping("/operate-log")
public class OperateLogController {

    private final OperateLogService operateLogService;


    /**
     * 查询操作日志列表
     * @param operateLogRequest 请求入参
     * @return {@link OperateLog} 操作日志
     */
    @ApiOperation(value = "[后台]查询操作日志列表")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:operateLog:query')")
    @PostMapping("/list")
    public ResponseResult<PageResult<OperateLog>> listOperateLog(@RequestBody OperateLogRequest operateLogRequest){
        return ResponseResult.success("查询日志列表成功！",operateLogService.listOperateLog(operateLogRequest));
    }


    /**
     * 批量删除操作日志信息
     * @param idRequest id集合
     * @return 是否批量删除成功
     */
    @ApiOperation(value = "[后台]批量删除操作日志信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:operateLog:delete')")
    @DeleteMapping("/batch/delete")
    public ResponseResult<?> batchDeleteOperateLog(@RequestBody IDRequest<Long> idRequest){
        operateLogService.batchDeleteOperateLog(idRequest);
        return ResponseResult.success("删除成功！");
    }


}
