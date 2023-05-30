package com.pumpkin.disease.service.impl;

import com.pumpkin.disease.entity.Role;
import com.pumpkin.disease.entity.RoleMenu;
import com.pumpkin.disease.mapper.RoleMenuMapper;
import com.pumpkin.disease.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.pumpkin.disease.constant.SystemConstant.Permission.SUPER_ADMIN_LABEL;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public Set<Long> listMenuIdByRoleId(Role role) {

        //region 通过校验则为超级管理员
        if (SUPER_ADMIN_LABEL.equals(role.getRoleLabel())) {
            return baseMapper.listAllIds();
        }
        //endregion

        return baseMapper.listRoleMenu(role.getId());
    }
}
