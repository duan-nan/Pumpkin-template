import {getRequest, postDataRequest} from "@/utils/axios/request";

/**
 * 获取当前用户信息
 * @returns {*}
 */
export const getUserInfo = () => {
    return getRequest('/user-info/getMy');
}

/**
 * 更新用户信息
 * @param params 请求参数
 * @returns {*}
 */
export const updateUserInfo = (params) => {
    return postDataRequest('/user-info/update/info', params);
}

/**
 * 更新用户头像
 * @param avatar 用户头像
 * @returns {*}
 */
export const updateUserAvatar = (avatar) => {
    return postDataRequest(`/user-info/update/avatar`, {"imageUrl": avatar});
}
