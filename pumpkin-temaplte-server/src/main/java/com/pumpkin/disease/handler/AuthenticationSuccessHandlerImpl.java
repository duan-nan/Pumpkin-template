package com.pumpkin.disease.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.entity.UserInfo;
import com.pumpkin.disease.enums.http.HttpCodeEnum;
import com.pumpkin.disease.service.LoginLogService;
import com.pumpkin.disease.service.UserInfoService;
import com.pumpkin.disease.utils.jwt.JwtUtil;
import com.pumpkin.disease.utils.user.UserUtil;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.UPDATE_DATA_FAIL;
import static com.pumpkin.disease.constant.SystemConstant.Charset.JSON_TYPE_UTF8_CHARSET;

/**
 * @author: Pumpkin
 * @description: 登录成功处理器
 * @since : 2022/12/2
 */
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final LoginLogService loginLogService;
    private final UserInfoService userInfoService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(JSON_TYPE_UTF8_CHARSET);
        loginLogService.insertNewLoginLog(request, true, HttpCodeEnum.USER_LOGIN_SUCCESS.getMessage());
        updateUserLoginTime();
        response.getWriter().write(JSON.toJSONString(ResponseResult.success(HttpCodeEnum.USER_LOGIN_SUCCESS, genderTokenForUser())));
    }

    /**
     * 为当前用户生产token
     *
     * @return Token令牌
     */
    private String genderTokenForUser() {
        User user = UserUtil.getUserInfo();
        return jwtUtil.createJwt(user.getUsername());
    }


    /**
     * 更新用户登陆时间
     */
    private void updateUserLoginTime() {
        User user = UserUtil.getUserInfo();
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, user.getUserInfoId()));
        userInfo.setLastLoginTime(LocalDateTime.now());
        AssertUtil.assertIsNotPass(!userInfoService.updateById(userInfo), UPDATE_DATA_FAIL);
    }

}
