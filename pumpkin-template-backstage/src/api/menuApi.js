import {deleteDataRequest, getRequest, postDataRequest} from "@/utils/axios/request";

/**
 * 查询路由菜单列表
 * @param isSelectButton 是否查询按钮
 * @returns {*}
 */
export const listMenu = (isSelectButton) => {
    return getRequest(`/menu/list/tree?isSelectButton=${isSelectButton}`)
}
/**
 * 根据菜单id查询菜单信息
 * @param menuId 菜单id
 * @returns {*}
 */
export const getMenuById = (menuId) => {
    return getRequest(`/menu/get/${menuId}`);
}
/**
 * 查询菜单权限信息
 * @returns {*}
 */
export const listMenuPerm = () => {
    return getRequest('/menu/list/perm');
}
/**
 * 查询所有菜单id集合
 * @returns {*}
 */
export const listAllMenuIds = () => {
    return getRequest('/menu/list/allIds')
}
/**
 * 添加菜单信息
 * @param menuInfo 菜单信息
 * @returns {*}
 */
export const saveMenu = (menuInfo) => {
    return postDataRequest('/menu/save', menuInfo);
}
/**
 * 更新菜单信息
 * @param menuInfo 菜单信息
 * @returns {*}
 */
export const updateMenu = (menuInfo) => {
    return postDataRequest('/menu/update', menuInfo);
}

/**
 * 删除菜单信息
 * @param idInfo id参数
 * @returns {*}
 */
export const deleteMenuById = (idInfo) => {
    return deleteDataRequest('/menu/delete', (idInfo instanceof Array) ? {ids: idInfo} : {id: idInfo})
}
