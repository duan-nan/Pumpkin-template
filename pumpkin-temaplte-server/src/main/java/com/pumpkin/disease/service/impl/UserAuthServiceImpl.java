package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.entity.UserAuth;
import com.pumpkin.disease.mapper.UserAuthMapper;
import com.pumpkin.disease.request.security.user.PasswordRequest;
import com.pumpkin.disease.service.UserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.utils.user.UserUtil;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.*;
import static com.pumpkin.disease.constant.SystemConstant.Number.NOT_DELETED;

/**
 * <p>
 * 用户权限表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Override
    public UserAuth getUserInfo() {
        //region 检查用户信息是否存在
        User userInfo = UserUtil.getUserInfo();
        AssertUtil.assertIsNotPass(AssertUtil.isNull(userInfo), QUERY_LOGIN_USER_INFO_ERROR);
        //endregion

        //region 检查用户信息是否存在
        UserAuth userAuth = baseMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getId, userInfo.getId())
                .eq(UserAuth::getIsDeleted, NOT_DELETED));
        AssertUtil.assertIsNotPass(AssertUtil.isNull(userAuth), QUERY_LOGIN_USER_INFO_ERROR);
        //endregion

        return userAuth;
    }

    @Override
    public void updateUserPassword(PasswordRequest passwordRequest) {
        //region 检查用户信息是否存在
        User userInfo = UserUtil.getUserInfo();
        AssertUtil.assertIsNotPass(AssertUtil.isNull(userInfo), QUERY_LOGIN_USER_INFO_ERROR);
        //endregion

        //region 检查用户输入的密码是否正确
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AssertUtil.assertIsNotPass(!passwordEncoder.matches(passwordRequest.getOldPassword(), userInfo.getPassword()), USER_OLD_PASSWORD_ERROR);
        //endregion

        //region 密码加密并更新
        UserAuth userAuth = baseMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getId, userInfo.getId())
                .eq(UserAuth::getIsDeleted, NOT_DELETED));
        AssertUtil.assertIsNotPass(AssertUtil.isNull(userAuth), QUERY_LOGIN_USER_INFO_ERROR);
        userAuth.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        AssertUtil.assertIsNotPass(AssertUtil.isUpdateOneFail(baseMapper.updateById(userAuth)), UPDATE_DATA_FAIL);
        //endregion
    }
}
