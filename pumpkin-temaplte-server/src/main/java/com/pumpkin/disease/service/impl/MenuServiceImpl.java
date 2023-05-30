package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.constant.SystemConstant;
import com.pumpkin.disease.entity.Menu;
import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.entity.website.OtherConfig;
import com.pumpkin.disease.enums.menu.MenuRouteTypeEnum;
import com.pumpkin.disease.enums.menu.MenuTypeEnum;
import com.pumpkin.disease.enums.website.WebsiteConfigEnum;
import com.pumpkin.disease.mapper.MenuMapper;
import com.pumpkin.disease.request.security.menu.MenuRequest;
import com.pumpkin.disease.response.security.menu.MenuDetailResponse;
import com.pumpkin.disease.response.security.menu.MenuPermResponse;
import com.pumpkin.disease.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.service.RoleHalfMenuService;
import com.pumpkin.disease.service.RoleMenuService;
import com.pumpkin.disease.service.WebsiteConfigService;
import com.pumpkin.disease.utils.bean.BeanCopyUtil;
import com.pumpkin.disease.utils.redis.RedisCache;
import com.pumpkin.disease.utils.user.UserUtil;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.*;
import static com.pumpkin.disease.constant.RedisPrefixConstant.MenuPrefix.*;
import static com.pumpkin.disease.constant.RedisPrefixConstant.MenuPrefix.CURRENT_ROLE_MENU_LIST;
import static com.pumpkin.disease.constant.SystemConstant.Number.NOT_DELETED;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final RedisCache redisCache;
    private final WebsiteConfigService websiteConfigService;
    private final RoleMenuService roleMenuService;
    private final RoleHalfMenuService roleHalfMenuService;

    @Override
    public List<Menu> listMenu(Boolean isSelectButton) {
        //region 若身份为超级管理员则返回所有数据
        if (UserUtil.userIsSuperAdmin()) {
            return listAllMenu(isSelectButton);
        }
        //endregion

        //region 查询Redis缓存中是否含有对应数据
        List<Menu> menuList = null;
        menuList = isSelectButton ? redisCache.getCacheList(ALL_MENU_LIST) : redisCache.getCacheList(NO_BUTTON_MENU_LIST);
        //endregion

        OtherConfig otherConfig = websiteConfigService.getWebsiteConfig(WebsiteConfigEnum.OTHER_MODULE.getWebsiteModuleCode());
        if (otherConfig.getMenuRouteType().equals(MenuRouteTypeEnum.ALL_MENU_ROUTE.getTypeCode())) {
            return listAllMenu(isSelectButton);
        } else if (otherConfig.getMenuRouteType().equals(MenuRouteTypeEnum.CURRENT_USER_HAVE_MENU_ROUTE.getTypeCode())) {
            return listCurrentUserMenu();
        }
        return null;
    }

    @Override
    public MenuDetailResponse getMenuById(Long menuId) {

        //region 查询Redis缓存中是否含有对应数据
        MenuDetailResponse menu = redisCache.getCacheObject(MENU_DETAIL_CACHE + menuId);
        //endregion

        if (AssertUtil.isNull(menu)) {
            //region 查询数据并进行判断
            Menu menuOne = baseMapper.selectOne(new LambdaQueryWrapper<Menu>()
                    .eq(Menu::getIsDeleted, NOT_DELETED)
                    .eq(Menu::getId, menuId));
            menu = BeanCopyUtil.copyObject(menuOne, MenuDetailResponse.class);

            Menu menuTwo = baseMapper.selectOne(new LambdaQueryWrapper<Menu>()
                    .eq(Menu::getIsDeleted, NOT_DELETED)
                    .eq(Menu::getId, menu.getParentId()));

            if (AssertUtil.isNotNull(menuTwo)) {
                menu.setParentName(menuTwo.getName());
            }

            AssertUtil.assertIsNotPass(AssertUtil.isNull(menu), QUERY_RECORD_NOT_EXISTED);
            //endregion

            //region 将数据进行缓存
            redisCache.setCacheObject(MENU_DETAIL_CACHE + menuId, menu);
            //endregion
        }

        return menu;
    }

    @Override
    public List<MenuPermResponse> listMenuPerm() {

        //region 查询Redis缓存中是否含有对应数据
        List<MenuPermResponse> menuPermResponseList = redisCache.getCacheList(PERM_MENU_LIST);
        //endregion

        if (AssertUtil.isListEmpty(menuPermResponseList)) {
            //region 查询菜单信息并进行数据封装
            List<Menu> menus = this.listMenu(true);
            AssertUtil.assertIsNotPass(AssertUtil.isNull(menus), QUERY_DATA_FAIL);
            menuPermResponseList = menus.stream()
                    .map(menu -> {
                        MenuPermResponse menuPermResponse = BeanCopyUtil.copyObject(menu, MenuPermResponse.class);
                        if (menu.getChildren().size() > 0) {
                            menuPermResponse.setChildren(copyChildrenData(menu.getChildren()));
                        }
                        return menuPermResponse;
                    })
                    .collect(Collectors.toList());
            //endregion

            //region 将数据进行缓存
            redisCache.setCacheList(PERM_MENU_LIST, menuPermResponseList);
            //endregion
        }

        return menuPermResponseList;
    }

    @Override
    public List<Long> listAllIds() {

        //region 查询Redis缓存中是否含有对应数据
        List<Long> idList = redisCache.getCacheList(ALL_MENU_ID_LIST);
        //endregion

        if (AssertUtil.isListEmpty(idList)) {
            idList = baseMapper.selectList(new LambdaQueryWrapper<Menu>()
                            .select(Menu::getId)
                            .eq(Menu::getIsDeleted, NOT_DELETED)
                            .ne(Menu::getMenuType, MenuTypeEnum.BUTTON.getCode()))
                    .stream()
                    .map(Menu::getId)
                    .collect(Collectors.toList());

            //region 将数据进行缓存
            redisCache.setCacheList(ALL_MENU_LIST, idList);
            //endregion
        }

        return idList;
    }


    @Override
    public void saveMenu(MenuRequest menuRequest) {
        //region 参数转换并进行非空校验
        Menu menu = BeanCopyUtil.copyObject(menuRequest, Menu.class);
        Optional.ofNullable(menu).orElseThrow(() -> new BaseException(PARAMETER_RECEIVE_ERROR));
        //endregion

        //region 检查数据是否存在
        if (checkMenuIsExisted(menu, false)) {
            throw new BaseException(DATA_ALREADY_EXISTED);
        }
        //endregion

        //region 新增菜单信息
        if (baseMapper.insert(menu) == 0) {
            throw new BaseException(SAVE_DATA_FAIL);
        }
        //endregion

        //region 清空缓存
        clearRedisCache();
        //endregion
    }

    @Override
    public void updateMenu(MenuRequest menuRequest) {
        //region 参数转换并进行非空校验
        if (menuRequest.getId() == null) {
            throw new BaseException(MISSING_NECESSARY_PARAMETER);
        }
        Menu menu = BeanCopyUtil.copyObject(menuRequest, Menu.class);
        Optional.ofNullable(menu).orElseThrow(() -> new BaseException(PARAMETER_RECEIVE_ERROR));
        //endregion

        //region 检查数据是否存在
        if (checkMenuIsExisted(menu, true)) {
            throw new BaseException(DATA_ALREADY_EXISTED);
        }
        //endregion

        //region 更新菜单信息
        if (baseMapper.updateById(menu) == 0) {
            throw new BaseException(UPDATE_DATA_FAIL);
        }
        //endregion

        //region 清空缓存
        clearRedisCache();
        redisCache.deleteObject(MENU_DETAIL_CACHE + menuRequest.getId());
        //endregion
    }

    @Override
    public void deleteMenu(IDRequest<Long> idRequest) {
        if (idRequest.getId() != null) {
            deleteMenuById(idRequest.getId());
            redisCache.deleteObject(MENU_DETAIL_CACHE + idRequest.getId());
        } else if (idRequest.getIds() != null) {
            idRequest.getIds().forEach(id -> {
                deleteMenuById(id);
                redisCache.deleteObject(MENU_DETAIL_CACHE + id);
            });
        } else {
            throw new BaseException(MISSING_NECESSARY_PARAMETER);
        }

        //region 清空缓存
        clearRedisCache();
        //endregion
    }

    @Override
    public Set<Long> listMenuContentIds() {
        return baseMapper.listMenuContentIds();
    }


    /**
     * 根据菜单id删除菜单
     *
     * @param id 菜单id
     */
    private void deleteMenuById(Long id) {
        //region 检查菜单是否拥有子级元素
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getParentId, id).eq(Menu::getIsDeleted, NOT_DELETED));

        if (count > 0) {
            throw new BaseException(PLEASE_DELETE_CHILDREN_DATA);
        }
        //endregion

        if (baseMapper.deleteById(id) == 0) {
            throw new BaseException(DELETE_DATA_FAIL);
        }
    }

    /**
     * 检查菜单是否已经存在（菜单名称、菜单路径、菜单组件、菜单权限 均相同则视为重复）
     *
     * @param menu     菜单信息
     * @param isUpdate 是否为更新操作
     */
    private boolean checkMenuIsExisted(Menu menu, boolean isUpdate) {
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getName, menu.getName())
                .eq(Menu::getPath, menu.getPath())
                .eq(Menu::getComponent, menu.getComponent())
                .eq(Menu::getPerm, menu.getPerm()));
        return isUpdate ? count > 1 : count > 0;
    }

    /**
     * 递归查询子菜单列表
     *
     * @param isSelectButton 是否查询按钮
     * @param menuList       菜单列表
     * @param parentMenuId   父级菜单id
     * @return {@link Menu} 菜单列表
     */
    private List<Menu> listChildMenu(Boolean isSelectButton, List<Menu> menuList, Long parentMenuId) {
        return menuList.stream()
                .filter(childMenu -> isSelectButton ? childMenu.getParentId().equals(parentMenuId) : (childMenu.getParentId().equals(parentMenuId) && !Objects.equals(childMenu.getMenuType(), MenuTypeEnum.BUTTON.getCode())))
                .peek(childMenu -> childMenu.setChildren(listChildMenu(isSelectButton, menuList, childMenu.getId())))
                .sorted(Comparator.comparingInt(Menu::getOrderNum))
                .collect(Collectors.toList());
    }


    /**
     * 递归拷贝数据
     *
     * @param menus children数据
     * @return {@link MenuPermResponse} 菜单权限信息
     */
    private List<MenuPermResponse> copyChildrenData(List<Menu> menus) {
        return menus.stream()
                .map(menu -> {
                    MenuPermResponse menuPermResponse = BeanCopyUtil.copyObject(menu, MenuPermResponse.class);
                    if (menu.getChildren().size() > 0) {
                        menuPermResponse.setChildren(copyChildrenData(menu.getChildren()));
                    }
                    return menuPermResponse;
                })
                .collect(Collectors.toList());
    }

    /**
     * 查询当前登录用户的菜单列表
     *
     * @return {@link Menu} 菜单列表
     */
    private List<Menu> listCurrentUserMenu() {

        //region 查询当前用户所拥有的权限id
        User user = UserUtil.getUserInfo();
        Set<Long> idList = new HashSet<>();
        idList.addAll(roleMenuService.listMenuIdByRoleId(user.getRole()));
        idList.addAll(roleHalfMenuService.listHalfMenuIdByRoleId(user.getRole()));
        //endregion

        //region 检查redis中是否含有缓存数据
        List<Menu> menuList = null;
        menuList = redisCache.getCacheList(CURRENT_ROLE_MENU_LIST + user.getRole().getId());
        //endregion

        if (AssertUtil.isListEmpty(menuList)) {
            List<Menu> allMenuList = baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getIsDeleted, NOT_DELETED));
            menuList = allMenuList.stream()
                    .filter(menu -> SystemConstant.Number.PARENT_DEFAULT_ID.equals(menu.getParentId()) && idList.contains(menu.getId()))
                    .peek(menu -> {
                        if (idList.contains(menu.getId())) {
                            menu.setChildren(listChildMenuInIds(idList, allMenuList, menu.getId()));
                        }
                    })
                    .sorted(Comparator.comparingInt(Menu::getOrderNum))
                    .collect(Collectors.toList());


            //region 添加缓存信息
            redisCache.setCacheList(CURRENT_ROLE_MENU_LIST + user.getRole().getId(), menuList);
            //endregion
        }

        return menuList;
    }

    /**
     * 查询所有菜单信息
     *
     * @param isSelectButton 是否查询按钮
     * @return {@link  Menu} 菜单列表
     */
    private List<Menu> listAllMenu(Boolean isSelectButton) {
        //region 查询Redis缓存中是否含有对应数据
        List<Menu> menuList = null;
        menuList = isSelectButton ? redisCache.getCacheList(ALL_MENU_LIST) : redisCache.getCacheList(NO_BUTTON_MENU_LIST);
        //endregion

        if (AssertUtil.isListEmpty(menuList)) {
            //region 查询所有菜单并进行处理
            List<Menu> allMenuList = baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getIsDeleted, NOT_DELETED));
            menuList = allMenuList.stream()
                    .filter(menu -> SystemConstant.Number.PARENT_DEFAULT_ID.equals(menu.getParentId()))
                    .peek(menu -> menu.setChildren(listChildMenu(isSelectButton, allMenuList, menu.getId())))
                    .sorted(Comparator.comparingInt(Menu::getOrderNum))
                    .collect(Collectors.toList());
            //endregion

            //region 将数据进行缓存
            if (isSelectButton) {
                redisCache.setCacheList(ALL_MENU_LIST, menuList);
            } else {
                redisCache.setCacheList(NO_BUTTON_MENU_LIST, menuList);
            }
            //endregion
        }

        return menuList;
    }


    /**
     * 查询置顶集合下的菜单列表
     * @param idList 菜单id集合
     * @param menuList 菜单集合
     * @param parentMenuId  父级菜单id
     * @return 菜单列表
     */
    private List<Menu> listChildMenuInIds(Set<Long> idList, List<Menu> menuList, Long parentMenuId) {
        return menuList.stream()
                .filter(childMenu -> (childMenu.getParentId().equals(parentMenuId) && !Objects.equals(childMenu.getMenuType(), MenuTypeEnum.BUTTON.getCode()) && idList.contains(childMenu.getId())))
                .peek(childMenu -> childMenu.setChildren(listChildMenuInIds(idList, menuList, childMenu.getId())))
                .sorted(Comparator.comparingInt(Menu::getOrderNum))
                .collect(Collectors.toList());
    }

    /**
     * 清空Redis缓存
     */
    private void clearRedisCache() {
        redisCache.deleteObject(ALL_MENU_LIST);
        redisCache.deleteObject(NO_BUTTON_MENU_LIST);
        redisCache.deleteObject(PERM_MENU_LIST);
        redisCache.deleteObject(ALL_MENU_ID_LIST);
    }

}
