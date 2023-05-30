package com.pumpkin.disease.service.impl;

import com.pumpkin.disease.entity.Role;
import com.pumpkin.disease.entity.RoleHalfMenu;
import com.pumpkin.disease.mapper.RoleHalfMenuMapper;
import com.pumpkin.disease.service.RoleHalfMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.pumpkin.disease.constant.SystemConstant.Permission.SUPER_ADMIN_LABEL;

/**
 * <p>
 * 角色-菜单 半选关联表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2023-05-29 15:17:18
 */
@Service
public class RoleHalfMenuServiceImpl extends ServiceImpl<RoleHalfMenuMapper, RoleHalfMenu> implements RoleHalfMenuService {

    @Override
    public Set<Long> listHalfMenuIdByRoleId(Role role) {
        return baseMapper.listRoleHalfMenu(role.getId());
    }
}
