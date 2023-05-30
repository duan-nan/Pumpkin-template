package com.pumpkin.disease.mapper;

import com.pumpkin.disease.entity.RoleHalfMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pumpkin.disease.request.security.role.RolePermRequest;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 角色-菜单 半选关联表 Mapper 接口
 * </p>
 *
 * @author Pumpkin
 * @since 2023-05-29 15:17:18
 */
public interface RoleHalfMenuMapper extends BaseMapper<RoleHalfMenu> {

    /**
     * 新增权限列表
     *
     * @param request 请求入参
     */
    void updatePermList(@Param("request") RolePermRequest request);

    /**
     * 查询半选菜单id
     * @param roleId 角色id
     * @return 菜单id列表
     */
    Set<Long> listRoleHalfMenu(@Param("roleId") String roleId);
}
