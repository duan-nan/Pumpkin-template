package com.pumpkin.disease.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pumpkin.disease.request.system.log.LoginLogRequest;
import com.pumpkin.disease.response.system.log.LoginLogResponse;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 登陆日志表 Mapper 接口
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    /**
     * 查询登录日志信息列表
     * @param page 分页参数
     * @param loginLogRequest 登陆日志请求入参类
     * @return {@link LoginLogResponse} 日志信息
     */
    IPage<LoginLogResponse> listLoginLog(Page<LoginLog> page, @Param("request") LoginLogRequest loginLogRequest);
}
