/**
 * 去除children为空的对象children属性
 * @param menuList
 * @returns {*[]}
 */
export const formatMenu = (menuList) => {


    let fmtRoutes = [];

    menuList.forEach((menu) => {
        // 判断children是否是数组 不为空则进行递归
        if (menu.children instanceof Array && menu.children.length > 0) {
            menu.children = formatMenu(menu.children);
        }

        if (menu.children instanceof Array && menu.children.length === 0) {
            let fmtRoute = {
                id: menu.id,
                name: menu.name,
                path: menu.path,
                perm: menu.perm,
                component: menu.component,
                parentId: menu.parentId,
                icon: menu.icon,
                orderNum: menu.orderNum,
                menuType: menu.menuType,
                isHidden: menu.isHidden,
                gmtCreate: menu.gmtCreate,
                remark: menu.remark
            };
            fmtRoutes.push(fmtRoute);
        } else {
            let fmtRoute = {
                id: menu.id,
                name: menu.name,
                path: menu.path,
                perm: menu.perm,
                component: menu.component,
                parentId: menu.parentId,
                icon: menu.icon,
                orderNum: menu.orderNum,
                menuType: menu.menuType,
                isHidden: menu.isHidden,
                gmtCreate: menu.gmtCreate,
                remark: menu.remark,
                children: menu.children
            };
            fmtRoutes.push(fmtRoute);
        }
    });
    return fmtRoutes;
};