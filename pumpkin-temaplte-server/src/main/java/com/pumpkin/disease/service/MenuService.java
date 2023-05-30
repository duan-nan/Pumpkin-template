package com.pumpkin.disease.service;

import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pumpkin.disease.request.security.menu.MenuRequest;
import com.pumpkin.disease.response.security.menu.MenuDetailResponse;
import com.pumpkin.disease.response.security.menu.MenuPermResponse;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface MenuService extends IService<Menu> {

    /**
     * [后台]查询后台菜单列表
     *
     * @param isSelectButton 是否查询按钮
     * @return {@link Menu} 菜单信息
     */
    List<Menu> listMenu(Boolean isSelectButton);

    /**
     * 查询指定菜单信息
     *
     * @param menuId 菜单id
     * @return {@link  MenuDetailResponse} 菜单
     */
    MenuDetailResponse getMenuById(Long menuId);

    /**
     * 查询菜单权限列表
     *
     * @return {@link MenuPermResponse} 菜单权限列表
     */
    List<MenuPermResponse> listMenuPerm();

    /**
     * 查询所有菜单id列表
     *
     * @return 所有菜单id集合
     */
    List<Long> listAllIds();

    /**
     * 新增菜单信息
     *
     * @param menuRequest 菜单请求入参
     */
    void saveMenu(MenuRequest menuRequest);

    /**
     * 更新菜单信息
     *
     * @param menuRequest 菜单请求入参
     */
    void updateMenu(MenuRequest menuRequest);

    /**
     * 删除菜单信息
     *
     * @param idRequest 菜单请求入参
     */
    void deleteMenu(IDRequest<Long> idRequest);

    /**
     * [后台]查询后台菜单id集合
     *
     * @return 菜单id信息
     */
    Set<Long> listMenuContentIds();
}
