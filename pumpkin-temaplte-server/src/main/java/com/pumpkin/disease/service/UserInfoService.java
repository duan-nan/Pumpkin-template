package com.pumpkin.disease.service;

import com.pumpkin.disease.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pumpkin.disease.request.security.user.UserInfoRequest;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 更新用户信息
     *
     * @param userInfoRequest 用户请求入参
     */
    void updateUserInfo(UserInfoRequest userInfoRequest);

    /**
     * 更新用户头像
     * @param avatar 用户头像
     */
    void updateUserAvatar(String avatar);
}
