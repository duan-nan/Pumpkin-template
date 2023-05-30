package com.pumpkin.disease.service;

import com.pumpkin.disease.entity.Role;
import com.pumpkin.disease.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 查询指定角色的菜单id列表
     *
     * @param role 角色
     * @return {@link Set} set集合
     */
    Set<Long> listMenuIdByRoleId(Role role);
}
