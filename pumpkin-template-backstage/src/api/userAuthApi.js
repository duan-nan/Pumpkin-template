import {postDataRequest} from "@/utils/axios/request";

/**
 * 更新用户密码
 * @param params 请求入参
 * @returns {*}
 */
export const updateUserPassword = (params) => {
    return postDataRequest("/user-auth/update/password",params);
};

