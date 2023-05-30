package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.entity.UserInfo;
import com.pumpkin.disease.mapper.UserInfoMapper;
import com.pumpkin.disease.request.security.user.UserInfoRequest;
import com.pumpkin.disease.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.utils.bean.BeanCopyUtil;
import com.pumpkin.disease.utils.user.UserUtil;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.UPDATE_DATA_FAIL;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public void updateUserInfo(UserInfoRequest userInfoRequest) {
        User user = UserUtil.getUserInfo();

        //region 数据封装并更新用户信息
        UserInfo userInfo = BeanCopyUtil.copyObject(userInfoRequest, UserInfo.class);
        userInfo.setId(user.getUserInfoId());
        AssertUtil.assertIsNotPass(AssertUtil.isUpdateOneFail(baseMapper.updateById(userInfo)), UPDATE_DATA_FAIL);
        //endregion
    }

    @Override
    public void updateUserAvatar(String avatar) {
        User user = UserUtil.getUserInfo();

        //region 数据封装并更新用户信息
        UserInfo userInfo = baseMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, user.getUserInfoId()));
        userInfo.setAvatar(avatar);
        AssertUtil.assertIsNotPass(AssertUtil.isUpdateOneFail(baseMapper.updateById(userInfo)), UPDATE_DATA_FAIL);
        //endregion
    }
}
