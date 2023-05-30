import {getRequest} from "@/utils/axios/request";

/**
 * 查询设备类型列表
 * @returns {*}
 */
export const listDeviceType = () => {
    return getRequest('/enum/device/type/list');
}

/**
 * 查询注册来源类型
 * @returns {*}
 */
export const listRegisteredSource = () => {
    return getRequest('/enum/registered/source/list');
}
