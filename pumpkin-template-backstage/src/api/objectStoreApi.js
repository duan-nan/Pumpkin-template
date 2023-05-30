import {deleteDataRequest, getRequest, postDataRequest, putDataRequest} from "@/utils/axios/request";

/**
 * 查询对象存储数据信息
 * @param condition 搜索条件
 * @returns {*}
 */
export const listObjectStore = (condition) => {
    return postDataRequest('/object-store/list', condition);
};


/**
 * 查询对象存储基础信息
 * @returns {*}
 */
export const listObjectStoreBasicInfo = () => {
    return getRequest('/object-store/list/basic/info');
};

/**
 * 查询对象存储下拉框信息
 * @returns {*}
 */
export const listObjectStoreSelect = () => {
    return getRequest('/object-store/list/select');
};

/**
 * 获取对象存储详细信息
 * @param id 对象存储id
 * @returns {*}
 */
export const getObjectStoreById = (id) => {
    return postDataRequest(`/object-store/get/${id}`);
};

/**
 * 添加对象存储信息
 * @param param 对象存储信息参数
 * @returns {*}
 */
export const saveObjectStore = (param) => {
    return postDataRequest('/object-store/save', param);
}

/**
 * 更新对象存储信息
 * @param param 对象存储信息参数
 * @returns {*}
 */
export const updateObjectStore = (param) => {
    return putDataRequest('/object-store/update', param);
}

/**
 * 更新对象存储信息状态
 * @param param 对象存储信息参数
 * @returns {*}
 */
export const updateObjectStoreStatus = (param) => {
    return putDataRequest('/object-store/updateStatus', param);
}

/**
 * 批量删除对象存储信息
 * @param idList id集合
 * @returns {*}
 */
export const batchDeleteObjectStore = (idList) => {
    return deleteDataRequest('/object-store/batch/delete', {ids: idList})
}
