import {deleteDataRequest, postDataRequest} from "@/utils/axios/request";


/**
 * 查询用户列表
 * @param condition 查询条件
 * @returns {*}
 */
export const listUser = (condition) => {
    return postDataRequest('/user/list', condition);
}

/**
 * 新增用户信息
 * @param params 请求入参
 * @returns {*}
 */
export const saveUser = (params) => {
    return postDataRequest('/user/save', params);
}

/**
 * 更新用户信息
 * @param params 请求入参
 * @returns {*}
 */
export const updateUser = (params) => {
    return postDataRequest('/user/update', params);
}

/**
 * 批量删除用户信息
 * @param idList id集合
 * @returns {*}
 */
export const batchDeleteUser = (idList) => {
    return deleteDataRequest('/user/batch/delete', {ids: idList});
}

/**
 * 重置用户面
 * @param id 用户id
 * @param password 用户密码
 * @returns {*}
 */
export const resetPassword = (id, password) => {
    return postDataRequest(`/user/reset/password/${id}`, {"password": password});
}
