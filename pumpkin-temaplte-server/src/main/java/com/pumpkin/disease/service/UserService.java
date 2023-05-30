package com.pumpkin.disease.service;

import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.request.security.user.UserConditionRequest;
import com.pumpkin.disease.request.security.user.UserRequest;
import com.pumpkin.disease.response.security.user.UserResponse;

/**
 * @author: Pumpkin
 * @description: 用户 服务类
 * @since : 2023/3/13
 */
public interface UserService {

    /**
     * 查询用户列表
     *
     * @param userConditionRequest 用户条件请求入参
     * @return {@link  UserResponse} 用户响应信息
     */
    PageResult<UserResponse> queryUserList(UserConditionRequest userConditionRequest);

    /**
     * 添加用户信息
     * @param userRequest 请求入参
     */
    void saveUser(UserRequest userRequest);

    /**
     * 更新用户信息
     * @param userRequest 请求入参
     */
    void updateUser(UserRequest userRequest);

    /**
     * 重置密码
     * @param id 用户id
     * @param password 密码
     */
    void resetUserPassword(String id, String password);

    /**
     * 批量删除用户信息
     * @param idRequest id请求入参
     */
    void batchDeleteUser(IDRequest<String> idRequest);

}
