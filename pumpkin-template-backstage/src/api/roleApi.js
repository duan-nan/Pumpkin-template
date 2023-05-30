import {deleteDataRequest, getRequest, postDataRequest} from "@/utils/axios/request";

/**
 * 查询角色列表
 * @param condition 查询条件
 * @returns {*}
 */
export const listRole = (condition) => {
    return postDataRequest('/role/list', condition);
}

/**
 * 查询角色信息
 * @param roleId 角色id
 * @returns {*}
 */
export const getRole = (roleId) => {
    return getRequest(`/role/get/${roleId}`);
}

/**
 * 查询角色基础信息
 * @returns {*}
 */
export const listRoleBasic = () => {
    return getRequest('/role/list/basic');
}

/**
 * 新增角色信息
 * @param roleParam 角色信息
 * @returns {*}
 */
export const saveRole = (roleParam) => {
    return postDataRequest('/role/save', roleParam);
}

/**
 * 更新角色信息
 * @param roleParam 角色信息
 * @returns {*}
 */
export const updateRole = (roleParam) => {
    return postDataRequest('/role/update', roleParam);
}


/**
 * 更新权限信息
 * @param permParam 权限参数
 * @returns {*}
 */
export const updatePerm = (permParam) => {
    return postDataRequest('/role/update/perm', permParam);
}

/**
 * 删除角色信息
 * @param idParam id参数
 * @returns {*}
 */
export const batchDeleteRole = (idParam) => {
    return deleteDataRequest('/role/delete', {ids: idParam});
}
