package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.Role;
import com.pumpkin.disease.entity.RoleHalfMenu;
import com.pumpkin.disease.entity.RoleMenu;
import com.pumpkin.disease.entity.UserAuth;
import com.pumpkin.disease.mapper.RoleHalfMenuMapper;
import com.pumpkin.disease.mapper.RoleMapper;
import com.pumpkin.disease.mapper.RoleMenuMapper;
import com.pumpkin.disease.mapper.UserAuthMapper;
import com.pumpkin.disease.request.security.role.RolePageRequest;
import com.pumpkin.disease.request.security.role.RolePermRequest;
import com.pumpkin.disease.request.security.role.RoleRequest;
import com.pumpkin.disease.response.security.role.RoleBasicResponse;
import com.pumpkin.disease.response.security.role.RoleMenuResponse;
import com.pumpkin.disease.response.security.role.RoleResponse;
import com.pumpkin.disease.service.MenuService;
import com.pumpkin.disease.service.RoleService;
import com.pumpkin.disease.utils.bean.BeanCopyUtil;
import com.pumpkin.disease.utils.redis.RedisCache;
import com.pumpkin.disease.utils.uuid.UUIDUtil;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.*;
import static com.pumpkin.disease.constant.RedisPrefixConstant.MenuPrefix.CURRENT_ROLE_MENU_LIST;
import static com.pumpkin.disease.constant.SystemConstant.Permission.SUPER_ADMIN_LABEL;
import static com.pumpkin.disease.constant.SystemConstant.Permission.SUPER_ADMIN_PERMISSION;

/**
 * <p>
 * 角色表信息 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private final MenuService menuService;
    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final UserAuthMapper userAuthMapper;
    private final RoleHalfMenuMapper roleHalfMenuMapper;
    private final RedisCache redisCache;


    @Override
    public Set<String> listRolePermission(Role role) {
        Set<String> permissionList = new HashSet<>();

        //region 通过校验则为超级管理员
        if (SUPER_ADMIN_LABEL.equals(role.getRoleLabel())) {
            permissionList.add(SUPER_ADMIN_PERMISSION);
            return permissionList;
        }
        //endregion

        return roleMapper.listPermissionByRoleId(role.getId());
    }

    @Override
    public PageResult<RoleResponse> listRole(RolePageRequest request) {
        IPage<RoleResponse> page = baseMapper.listRoleByPage(new Page<Role>(request.getPageNow(), request.getPageSize()), request);
        return new PageResult<RoleResponse>(page.getTotal(), page.getRecords());
    }

    @Override
    public RoleMenuResponse getRoleByRoleId(String roleId) {


        //region 参数非空校验
        AssertUtil.assertIsNotPass(AssertUtil.isEmpty(roleId), MISSING_NECESSARY_PARAMETER);
        //endregion

        //region 查询角色信息
        RoleMenuResponse response = baseMapper.getRoleByRoleId(roleId);
        if (null != response) {
            response.setMenuIdList(roleMenuMapper.listRoleMenu(roleId));
        }
        //endregion

        return response;
    }

    @Override
    public List<RoleBasicResponse> listBasicRole() {
        List<RoleBasicResponse> list = baseMapper.listBasicRole();
        return list;
    }

    @Override
    public void saveRole(RoleRequest request) {
        //region 数据转换并且进行非空判断
        Role role = BeanCopyUtil.copyObject(request, Role.class);
        AssertUtil.assertIsNotPass(AssertUtil.isNull(role), PARAMETER_RECEIVE_ERROR);
        //endregion

        //region 检测角色是否已经存在
        AssertUtil.assertIsNotPass(checkIsRoleExisted(role, false), DATA_ALREADY_EXISTED);
        //endregion

        //region 新增数据
        role.setId(UUIDUtil.get32UUID());
        AssertUtil.assertIsNotPass(AssertUtil.isSaveFail(baseMapper.insert(role)), SAVE_DATA_FAIL);
        //endregion
    }

    @Override
    public void updateRole(RoleRequest request) {
        //region 数据转换并且进行非空判断
        AssertUtil.assertIsNotPass(AssertUtil.isNull(request.getId()), MISSING_NECESSARY_PARAMETER);
        Role role = BeanCopyUtil.copyObject(request, Role.class);
        AssertUtil.assertIsNotPass(AssertUtil.isNull(role), PARAMETER_RECEIVE_ERROR);
        //endregion

        //region 检测角色是否已经存在
        AssertUtil.assertIsNotPass(checkIsRoleExisted(role, true), DATA_ALREADY_EXISTED);
        //endregion

        //region 更新数据
        AssertUtil.assertIsNotPass(AssertUtil.isUpdateOneFail(baseMapper.updateById(role)), UPDATE_DATA_FAIL);
        //endregion
    }

    @Override
    public void updatePerm(RolePermRequest request) {
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, request.getRoleId()));
        roleHalfMenuMapper.delete(new LambdaQueryWrapper<RoleHalfMenu>().eq(RoleHalfMenu::getRoleId, request.getRoleId()));
        if (!request.getMenuIds().isEmpty()) {
            roleMenuMapper.updatePermList(request);
        }
        if (!request.getHalfCheckedIds().isEmpty()) {
            roleHalfMenuMapper.updatePermList(request);
        }
        redisCache.deleteObject(CURRENT_ROLE_MENU_LIST + request.getRoleId());
    }

    @Override
    public void deleteRole(IDRequest<String> idRequest) {
        if (idRequest.getId() != null) {
            deleteRoleById(idRequest.getId());
        } else if (idRequest.getIds() != null) {
            idRequest.getIds().forEach(this::deleteRoleById);
        } else {
            throw new BaseException(MISSING_NECESSARY_PARAMETER);
        }
    }

    /**
     * 删除角色信息
     *
     * @param roleId 角色id
     */
    private void deleteRoleById(String roleId) {
        //region 检查角色是否已经与用户绑定
        Long count = userAuthMapper.selectCount(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getUserRoleId, roleId));

        if (count > 0) {
            throw new BaseException(PLEASE_DELETE_USER_FIRST);
        }
        //endregion

        if (baseMapper.deleteById(roleId) == 0) {
            throw new BaseException(DELETE_DATA_FAIL);
        }
    }

    /**
     * 检测角色是否存在(角色名称、角色标识相同即为重复)
     *
     * @param role     角色
     * @param isUpdate 是否为更新操作
     * @return true 已经存在  false 不存在
     */
    private boolean checkIsRoleExisted(Role role, boolean isUpdate) {
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleName, role.getRoleName())
                .eq(Role::getRoleLabel, role.getRoleLabel()));
        return isUpdate ? count > 1 : count > 0;
    }
}
