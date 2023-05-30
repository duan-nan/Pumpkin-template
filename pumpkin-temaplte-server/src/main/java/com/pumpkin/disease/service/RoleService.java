package com.pumpkin.disease.service;

import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pumpkin.disease.request.security.role.RolePageRequest;
import com.pumpkin.disease.request.security.role.RolePermRequest;
import com.pumpkin.disease.request.security.role.RoleRequest;
import com.pumpkin.disease.response.security.role.RoleBasicResponse;
import com.pumpkin.disease.response.security.role.RoleMenuResponse;
import com.pumpkin.disease.response.security.role.RoleResponse;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表信息 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询当前角色的权限列表
     *
     * @param role 角色信息
     * @return {@link Set} 角色权限列表
     */
    Set<String> listRolePermission(Role role);

    /**
     * 分页查询角色列表
     *
     * @param rolePageRequest 角色请求入参
     * @return {@link  RoleResponse} 角色信息列表
     */
    PageResult<RoleResponse> listRole(RolePageRequest rolePageRequest);

    /**
     * 获取指定角色详细信息
     * @param roleId 角色id
     * @return {@link RoleMenuResponse} 角色信息
     */
    RoleMenuResponse getRoleByRoleId(String roleId);

    /**
     * 查询角色基础信息列表
     * @return {@link RoleBasicResponse} 角色信息
     */
    List<RoleBasicResponse> listBasicRole();

    /**
     * 新增角色信息
     * @param request 角色请求入参
     */
    void saveRole(RoleRequest request);

    /**
     * 更新角色信息
     * @param request 角色请求入参
     */
    void updateRole(RoleRequest request);

    /**
     * 更新角色权限列表
     * @param rolePermRequest 请求入参
     */
    void updatePerm(RolePermRequest rolePermRequest);

    /**
     * 删除角色信息
     * @param idRequest 请求入参
     */
    void deleteRole(IDRequest<String> idRequest);



}
