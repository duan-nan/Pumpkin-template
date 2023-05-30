package com.pumpkin.disease.service;

import com.pumpkin.disease.entity.Role;
import com.pumpkin.disease.entity.RoleHalfMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色-菜单 半选关联表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2023-05-29 15:17:18
 */
public interface RoleHalfMenuService extends IService<RoleHalfMenu> {

    /**
     * 查询指定角色的半选菜单id列表
     *
     * @param role 角色
     * @return {@link Set} set集合
     */
    Set<Long> listHalfMenuIdByRoleId(Role role);
}
