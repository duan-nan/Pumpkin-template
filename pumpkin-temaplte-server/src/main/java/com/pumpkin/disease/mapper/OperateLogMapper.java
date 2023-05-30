package com.pumpkin.disease.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.entity.OperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pumpkin.disease.request.system.log.OperateLogRequest;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface OperateLogMapper extends BaseMapper<OperateLog> {

    /**
     * 查询日志列表
     *
     * @param operateLogPage 分页对象
     * @param request        请求入参
     * @return {@link OperateLog} 操作日志
     */
    IPage<OperateLog> listOperateLog(Page<OperateLog> operateLogPage, @Param("request") OperateLogRequest request);
}
