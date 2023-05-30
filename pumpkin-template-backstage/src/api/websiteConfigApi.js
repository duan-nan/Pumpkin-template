import {getRequest, postDataRequest} from "@/utils/axios/request";


/**
 * 获取网站配制信息
 * @returns {*}
 */
export const getWebsiteConfig = (websiteType) => {
    return getRequest(`/website-config/get/${websiteType}`);
};


/**
 * 更新网站配制信息
 * @param params 配制参数
 */
export const updateWebsiteConfig = (params) => {
    return postDataRequest('/website-config/update', params);
};


/**
 * 查询菜单路由
 * @returns {*}
 */
export const listMenuRoute = () => {
    return getRequest('/enum/menu/route/list');
};
