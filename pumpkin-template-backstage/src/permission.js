import {listMenu} from "@/api/menuApi";

/**
 * 初始化路由菜单列表
 * @param store store
 * @param router router
 */
export const initMenuRouter = (store, router) => {
    listMenu(false).then(res => {
        if (res.success) {
            let formatMenu = formatMenuRoute(res.data, null, null, router);
            router.options.routes.forEach(routerItem => router.addRoute(routerItem))
            store.commit('UPDATE_MENU_ROUTERS_LIST', formatMenu);
        }
    });
}


function formatMenuRoute(menuRouterList, grandPa, parent, router) {
    // 初始化一个数据存放数据
    let formatMenu = [];

    menuRouterList.forEach(menuRouter => {

        if (menuRouter.children instanceof Array && menuRouter.children.length > 0) {
            menuRouter.children = formatMenuRoute(menuRouter.children, parent, menuRouter, router);
        }

        let fmtMenuRoute = {
            id: menuRouter.id,
            meta: {
                currentId: menuRouter.id,
                currentIcon: menuRouter.icon,
                grandFather: {
                    parentName: grandPa === null ? grandPa : grandPa.name,
                    parentIcon: grandPa === null ? grandPa : grandPa.icon,
                },
                father: {
                    parentName: parent === null ? parent : parent.name,
                    parentIcon: parent === null ? parent : parent.icon,
                }
            },
            path: menuRouter.path,
            name: menuRouter.name,
            perm: menuRouter.perm,
            menuType: menuRouter.menuType,
            icon: menuRouter.icon,
            children: menuRouter.children,
            component(resolve) {
                if (menuRouter.component !== '#') {
                    require(['@/views' + menuRouter.component + '.vue'], resolve);
                }
            }
        };

        if (fmtMenuRoute.menuType === 'C') {
            router.options.routes[2].children.push(fmtMenuRoute);
        }

        formatMenu.push(fmtMenuRoute);
    });

    return formatMenu;
}
