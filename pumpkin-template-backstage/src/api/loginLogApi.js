import {deleteDataRequest, getRequest, postDataRequest} from "@/utils/axios/request";


/**
 * 查询登录日志列表
 * @param condition 请求参数
 * @returns {Promise<AxiosResponse<any>>}
 */
export const listLoginLog = (condition) => {
    return postDataRequest('/login-log/list', condition);
};


/**
 * 批量删除登陆日志
 * @param idRequest id列表
 */
export const batchDeleteLoginLog = (idRequest) => {
    return deleteDataRequest('/login-log/batchDelete',{ids:idRequest})
};
