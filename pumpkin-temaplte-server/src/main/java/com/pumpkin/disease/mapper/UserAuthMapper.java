package com.pumpkin.disease.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.entity.UserAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pumpkin.disease.request.security.user.UserConditionRequest;
import com.pumpkin.disease.request.security.user.UserRequest;
import com.pumpkin.disease.response.security.user.UserResponse;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户权限表 Mapper 接口
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface UserAuthMapper extends BaseMapper<UserAuth> {

    /**
     * 查询用户信息列表
     * @param page 分页对象
     * @param request 请求入参
     * @return {@link UserResponse} 用户返回信息
     */
    IPage<UserResponse> queryUserList(Page<UserAuth> page,@Param("request") UserConditionRequest request);

    /**
     * 更新用户权限信息
     * @param userRequest 请求入参
     */
    void updateUserAuth(@Param("request") UserRequest userRequest);

    /**
     * 重置密码
     * @param id 用户id
     * @param encodePassword 加密后的密码
     */
    Integer resetUserPassword(@Param("id") String id, @Param("password") String encodePassword);

}
