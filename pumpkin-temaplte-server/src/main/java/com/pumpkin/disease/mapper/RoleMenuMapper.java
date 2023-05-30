package com.pumpkin.disease.mapper;

import com.pumpkin.disease.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pumpkin.disease.request.security.role.RolePermRequest;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 查询指定角色所拥有的的菜单列表
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    Set<Long> listRoleMenu(@Param("roleId") String roleId);

    /**
     * 新增权限列表
     *
     * @param request 请求入参
     */
    void updatePermList(@Param("request") RolePermRequest request);

    /**
     * 查询所有菜单id
     *
     * @return 菜单集合id
     */
    Set<Long> listAllIds();
}
