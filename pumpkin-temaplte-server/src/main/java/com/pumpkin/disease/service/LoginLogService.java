package com.pumpkin.disease.service;

import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pumpkin.disease.request.system.log.LoginLogRequest;
import com.pumpkin.disease.response.system.log.LoginLogResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 登陆日志表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 查询登录日志信息列表
     * @param loginLogRequest 登陆日志请求入参类
     * @return {@link LoginLogResponse} 日志信息
     */
    PageResult<LoginLogResponse> listLoginLog(LoginLogRequest loginLogRequest);

    /**
     * 新增登录日志信息
     * @param request request
     * @param isLoginSuccess 是否登录成功
     * @param message 响应信息
     */
    void insertNewLoginLog(HttpServletRequest request, boolean isLoginSuccess,String message);

    /**
     * 批量删除日志信息
     * @param idRequest id集合
     * @return 是否删除成功
     */
    void batchDeleteLoginLog(IDRequest<Long> idRequest);

}
