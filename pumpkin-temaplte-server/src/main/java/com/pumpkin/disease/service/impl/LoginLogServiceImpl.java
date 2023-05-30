package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import com.pumpkin.disease.entity.LoginLog;
import com.pumpkin.disease.request.system.log.LoginLogRequest;
import com.pumpkin.disease.response.system.log.LoginLogResponse;
import com.pumpkin.disease.service.LoginLogService;
import com.pumpkin.disease.mapper.LoginLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.utils.ip.IpUtil;
import com.pumpkin.disease.utils.user.UserUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 登陆日志表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {


    @Override
    public PageResult<LoginLogResponse> listLoginLog(LoginLogRequest loginLogRequest) {
        IPage<LoginLogResponse> iPage = baseMapper.listLoginLog(new Page<LoginLog>(loginLogRequest.getPageNow(), loginLogRequest.getPageSize()), loginLogRequest);
        return new PageResult<LoginLogResponse>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public void insertNewLoginLog(HttpServletRequest request, boolean isLoginSuccess, String message) {
        if (baseMapper.insert(genderLoginLogEntity(request, isLoginSuccess, message)) == 0) {
            throw new BaseException(BusinessErrorConstant.SimpleError.SAVE_DATA_FAIL);
        }
    }

    @Override
    public void batchDeleteLoginLog(IDRequest<Long> idRequest) {
        if (idRequest.getIds().size() > 0) {
            idRequest.getIds().forEach(baseMapper::deleteById);
        }
    }


    /**
     * 构造登录日志实体对象
     *
     * @param request        request
     * @param isLoginSuccess 是否登录成功
     * @param message        响应信息
     * @return {@link LoginLog} 登录日志实体类
     */
    private LoginLog genderLoginLogEntity(HttpServletRequest request, boolean isLoginSuccess, String message) {
        UserAgent userAgent = IpUtil.getUserAgent(request);
        String ipAddress = IpUtil.getIpAddress(request);
        String ipSource = IpUtil.getIpSource(ipAddress);
        return new LoginLog() {{
            setUsername(isLoginSuccess ? UserUtil.getUserInfo().getUsername() : request.getParameter("username"));
            setIpAddress(ipAddress);
            setIpSource(ipSource);
            setBrowser(userAgent.getBrowser().getName());
            setOs(userAgent.getOperatingSystem().getName());
            setLoginStatus((byte) (isLoginSuccess ? 1 : 0));
            setResponseMessage(message);
        }};
    }
}
