import {getRequest, postParamsRequest} from "@/utils/axios/request";

/**
 * 用户登陆
 * @param params 请求
 * @returns {*}
 */
export const userLogin = (params) => {
    return postParamsRequest('/user/login', params);
};

/**
 * 用户注销登陆
 * @returns {*}
 */
export const userLogout = () => {
    return getRequest('/user/logout');
}

/**
 * 获取系统资源信息
 * @returns {*}
 */
export const getSystemMonitorInfo = () => {
    return getRequest('/monitor/system');
}
