package com.pumpkin.disease.service;

import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.OperateLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pumpkin.disease.request.system.log.OperateLogRequest;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface OperateLogService extends IService<OperateLog> {

    /**
     * 查询操作日志列表
     * @param operateLogRequest 请求入参
     * @return {@link OperateLog} 操作日志
     */
    PageResult<OperateLog> listOperateLog(OperateLogRequest operateLogRequest);


    /**
     * 批量删除操作日志信息
     * @param idRequest id集合
     * @return 是否批量删除成功
     */
    void batchDeleteOperateLog(IDRequest<Long> idRequest);
}
