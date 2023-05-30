package com.pumpkin.disease.utils.user;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.enums.http.HttpCodeEnum;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.pumpkin.disease.constant.SystemConstant.Permission.ADMIN_LABEL;
import static com.pumpkin.disease.constant.SystemConstant.Permission.SUPER_ADMIN_LABEL;

/**
 * @author: Pumpkin
 * @description: 用户信息工具类
 * @since : 2022/12/29
 */
public class UserUtil {

    public static User getUserInfo() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof String) {
            throw new BaseException(HttpCodeEnum.USER_IDENTITY_LOAD_FAIL);
        }
        return (User) object;
    }


    /**
     * 检测当前用户是否是管理员或超级管理员
     * @return true 为管理员或超级管理员  false 普通用户或其他身份用户
     */
    public static boolean userIsAdmin() {
        return SUPER_ADMIN_LABEL.equals(getUserInfo().getRole().getRoleLabel()) || ADMIN_LABEL.equals(getUserInfo().getRole().getRoleLabel());
    }

    /**
     * 检测当前用户是否是超级管理员
     * @return true 超级管理员  false 普通用户或其他身份用户
     */
    public static boolean userIsSuperAdmin() {
        return SUPER_ADMIN_LABEL.equals(getUserInfo().getRole().getRoleLabel());
    }


}
