package com.pumpkin.disease.base.permission;

import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.utils.user.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.pumpkin.disease.constant.SystemConstant.Permission.SUPER_ADMIN_PERMISSION;

/**
 * @author: Pumpkin
 * @description: 权限校验服务
 * @since : 2023/3/7
 */
@Slf4j
@RequiredArgsConstructor
@Service(value = "mznPerms")
public class PermissionServiceImpl {

    /**
     * 检查当前用户是否拥有所需权限
     * @param permission 所需权限
     * @return true 含有   false 不含有
     */
    public boolean checkCurrentUserIsHasPerm(String permission){
        if (StringUtils.isEmpty(permission)){
            return false;
        }
        User user = UserUtil.getUserInfo();
        return verifyPermission(user.getPermissionList(),permission);
    }


    /**
     * 权限校验
     * @param permissionList 用户权限列表
     * @param permission 所需权限
     * @return true 用户拥有   false不拥有
     */
    private boolean verifyPermission(Set<String> permissionList, String permission) {
        return permissionList.contains(StringUtils.trim(permission)) || permissionList.contains(SUPER_ADMIN_PERMISSION);
    }

}
