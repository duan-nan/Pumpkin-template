import {deleteDataRequest, postDataRequest} from "@/utils/axios/request";


/**
 * 查询操作日志列表
 * @param condition 查询条件
 * @returns {*}
 */
export const listOperateLog = (condition) => {
    return postDataRequest('/operate-log/list',condition);
}

/**
 * 批量删除操作日志信息
 * @param idRequest id集合
 * @returns {*}
 */
export const batchDeleteOperateLog = (idRequest) => {
    return deleteDataRequest('/operate-log/batch/delete',{ids:idRequest})
}
