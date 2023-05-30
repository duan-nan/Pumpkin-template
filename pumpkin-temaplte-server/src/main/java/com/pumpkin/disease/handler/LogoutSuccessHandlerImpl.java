package com.pumpkin.disease.handler;

import com.alibaba.fastjson.JSON;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.enums.http.HttpCodeEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.pumpkin.disease.constant.SystemConstant.Charset.JSON_TYPE_UTF8_CHARSET;

/**
 * @author: Pumpkin
 * @description: 注销成功处理器
 * @since : 2022/12/2
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(JSON_TYPE_UTF8_CHARSET);
        response.getWriter().write(JSON.toJSONString(ResponseResult.success(HttpCodeEnum.USER_LOGOUT_SUCCESS)));
    }
}
