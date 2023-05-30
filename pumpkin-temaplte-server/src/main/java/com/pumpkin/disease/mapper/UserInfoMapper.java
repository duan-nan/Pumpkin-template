package com.pumpkin.disease.mapper;

import com.pumpkin.disease.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pumpkin.disease.request.security.user.UserRequest;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 更新用户信息
     * @param userRequest 请求入参
     * @param userInfoId 用户id
     */
    void updateUserInfo(@Param("request") UserRequest userRequest, @Param("id") Long userInfoId);
}
