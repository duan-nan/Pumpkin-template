package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.entity.Role;
import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.entity.UserAuth;
import com.pumpkin.disease.entity.UserInfo;
import com.pumpkin.disease.enums.device.DeviceTypeEnum;
import com.pumpkin.disease.enums.source.RegisteredSourceEnum;
import com.pumpkin.disease.service.UserAuthService;
import com.pumpkin.disease.service.UserInfoService;
import com.pumpkin.disease.enums.http.HttpCodeEnum;
import com.pumpkin.disease.service.RoleService;
import com.pumpkin.disease.utils.agent.UserAgentUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;

/**
 * @author: Pumpkin
 * @description: 登录逻辑重写
 * @since : 2022/12/2
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final HttpServletRequest request;
    private final UserAuthService userAuthService;
    private final UserInfoService userInfoService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = getUserAuthInfo(username);
        UserInfo userInfo = getUserBasicInfo(userAuth.getUserInfoId());
        return convertToUser(request, userAuth, userInfo);
    }


    /**
     * 用户数据转换
     *
     * @param request  request请求
     * @param userAuth 用户权限信息
     * @param userInfo 用户基础信息
     * @return {@link User} 封装后的用户信息
     */
    private User convertToUser(HttpServletRequest request, UserAuth userAuth, UserInfo userInfo) {

        //region 获取请求用户ip地址相关信息
        String ipAddress = UserAgentUtil.getIpAddress(request);
        String ipSource = UserAgentUtil.getIpSource(ipAddress);
        UserAgent userAgent = UserAgentUtil.getUserAgent(request);
        String browser = userAgent.getBrowser().getName();
        String os = userAgent.getOperatingSystem().getName();
        //endregion

        //region 查询用户角色信息并查询对应权限列表
        Role role = roleService.getOne(new LambdaQueryWrapper<Role>().eq(Role::getId, userAuth.getUserRoleId()));
        Optional.ofNullable(role).orElseThrow(() -> new BaseException(HttpCodeEnum.USER_IDENTITY_LOAD_FAIL));
        Set<String> permissionList = roleService.listRolePermission(role);
        //endregion

        return new User() {{
            setId(userAuth.getId());
            setUserInfoId(userAuth.getUserInfoId());
            setUsername(userAuth.getUsername());
            setPassword(userAuth.getPassword());
            setNickname(userInfo.getNickname());
            setRole(role);
            setLoginType(userAuth.getLoginType());
            setAvatar(userInfo.getAvatar());
            setGender(userInfo.getGender());
            setEmail(userInfo.getEmail());
            setPersonIntro(userInfo.getPersonIntro());
            setDeviceType(DeviceTypeEnum.getDeviceTypeByCode(userInfo.getDeviceType()).getDeviceName());
            setRegisteredSource(RegisteredSourceEnum.getRegisteredSourceEnumByCode(userInfo.getRegisteredSource()).getRegisteredSourceName());
            setIpAddress(ipAddress);
            setIpSource(ipSource);
            setGmtCreate(userInfo.getGmtCreate());
            setGmtUpdate(userInfo.getGmtUpdate());
            setLastLoginTime(userInfo.getLastLoginTime());
            setEmailLogin(userAuth.getEmailLogin());
            setIsDisabled(userAuth.getIsDisabled());
            setBrowser(browser);
            setOs(os);
            setPermissionList(permissionList);
        }};
    }


    /**
     * 根据用户名查询用户权限信息
     *
     * @param username 用户名
     * @return {@link  UserAuth} 用户权限信息
     */
    private UserAuth getUserAuthInfo(String username) {

        //region 用户名非空校验
        if (StringUtils.isEmpty(username)) {
            throw new BaseException(HttpCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //endregion

        //region 用户权限信息查询并校验
        UserAuth userAuth = userAuthService.getOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getUsername, username)
                .eq(UserAuth::getIsDeleted, 0));
        Optional.ofNullable(userAuth).orElseThrow(() -> new BaseException(HttpCodeEnum.USERNAME_OR_PASSWORD_ERROR));
        if (userAuth.getIsDisabled()) {
            throw new BaseException(HttpCodeEnum.ACCOUNT_IS_DISABLED);
        }
        //endregion

        return userAuth;
    }


    /**
     * 根据用户信息Id查询用户权限信息
     *
     * @param userInfoId 用户信息Id
     * @return {@link  UserInfo} 用户信息
     */
    private UserInfo getUserBasicInfo(Long userInfoId) {
        //region 用户基础信息查询并校验
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, userInfoId));
        Optional.ofNullable(userInfo).orElseThrow(() -> new BaseException(HttpCodeEnum.USER_INFO_LOAD_FAIL));
        //endregion

        return userInfo;
    }


}
