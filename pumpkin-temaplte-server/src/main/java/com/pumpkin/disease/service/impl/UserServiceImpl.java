package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.UserAuth;
import com.pumpkin.disease.entity.UserInfo;
import com.pumpkin.disease.entity.website.OtherConfig;
import com.pumpkin.disease.mapper.UserAuthMapper;
import com.pumpkin.disease.mapper.UserInfoMapper;
import com.pumpkin.disease.request.security.user.UserConditionRequest;
import com.pumpkin.disease.request.security.user.UserRequest;
import com.pumpkin.disease.response.security.user.UserResponse;
import com.pumpkin.disease.service.UserService;
import com.pumpkin.disease.service.WebsiteConfigService;
import com.pumpkin.disease.utils.ip.IpUtil;
import com.pumpkin.disease.utils.uuid.UUIDUtil;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.*;

/**
 * @author: Pumpkin
 * @description: 用户服务实现类
 * @since : 2023/3/13
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final HttpServletRequest request;
    private final UserInfoMapper userInfoMapper;
    private final UserAuthMapper userAuthMapper;
    private final WebsiteConfigService websiteConfigService;

    @Override
    public PageResult<UserResponse> queryUserList(UserConditionRequest request) {
        IPage<UserResponse> iPage = userAuthMapper.queryUserList(new Page<UserAuth>(request.getPageNow(), request.getPageSize()), request);
        return new PageResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Transactional(rollbackFor = BaseException.class)
    @Override
    public void saveUser(UserRequest userRequest) {
        //region 添加用户信息
        UserInfo userInfo = genderUserInfo(userRequest);
        AssertUtil.assertIsNotPass(AssertUtil.isSaveFail(userInfoMapper.insert(userInfo)), SAVE_DATA_FAIL);
        //endregion

        //region 添加用户权限
        AssertUtil.assertIsNotPass(AssertUtil.isSaveFail(userAuthMapper.insert(genderUserAuth(userRequest, userInfo.getId()))), SAVE_DATA_FAIL);
        //endregion
    }

    @Transactional(rollbackFor = BaseException.class)
    @Override
    public void updateUser(UserRequest userRequest) {
        //region 参数校验
        AssertUtil.assertIsNotPass(AssertUtil.isNull(userRequest.getId()), MISSING_NECESSARY_PARAMETER);
        //endregion

        //region 数据更新
        UserAuth userAuth = userAuthMapper.selectById(userRequest.getId());
        userAuthMapper.updateUserAuth(userRequest);
        userInfoMapper.updateUserInfo(userRequest, userAuth.getUserInfoId());
        //endregion
    }

    @Override
    public void resetUserPassword(String id, String password) {
        //region 参数校验
        AssertUtil.assertIsNotPass(AssertUtil.isNull(id), MISSING_NECESSARY_PARAMETER);
        AssertUtil.assertIsNotPass(AssertUtil.isNull(password), MISSING_NECESSARY_PARAMETER);
        //endregion

        //region 参数转换并进行更新
        String encodePassword = new BCryptPasswordEncoder().encode(password);
        AssertUtil.assertIsNotPass(AssertUtil.isUpdateOneFail(userAuthMapper.resetUserPassword(id, encodePassword)), UPDATE_DATA_FAIL);
        //endregion
    }

    @Override
    public void batchDeleteUser(IDRequest<String> idRequest) {
        if (AssertUtil.isNotNull(idRequest) && idRequest.getIds().size() > 0) {
            idRequest.getIds().forEach(userAuthMapper::deleteById);
        }
    }

    /**
     * 构造用户权限
     *
     * @param userRequest 请求入参
     * @param userInfoId  用户信息id
     */
    private UserAuth genderUserAuth(UserRequest userRequest, Long userInfoId) {
        //region 数据封装
        return new UserAuth() {{
            setId(UUIDUtil.get32UUID());
            setUsername(userRequest.getUsername());
            setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
            setUserRoleId(userRequest.getRoleId());
            setUserInfoId(userInfoId);
            setIsDisabled(userRequest.getIsDisabled() == 1);
        }};
        //endregion
    }

    /**
     * 添加用户信息
     *
     * @param userRequest 请求入参
     * @return {@link  UserInfo} 用户信息对象
     */
    private UserInfo genderUserInfo(UserRequest userRequest) {
        String ipAddress = IpUtil.getIpAddress(request);
        String ipSource = IpUtil.getIpSource(ipAddress);

        OtherConfig otherConfig = websiteConfigService.getWebsiteConfig(4);
        AssertUtil.assertIsNotPass(AssertUtil.isNull(otherConfig), QUERY_DATA_FAIL);

        //region 数据封装并进行新增
        return new UserInfo() {{
            setNickname(userRequest.getNickname());
            setAvatar(AssertUtil.isNull(userRequest.getAvatar()) ? otherConfig.getUserAvatar() : userRequest.getAvatar());
            setGender(userRequest.getGender());
            setEmail(userRequest.getEmail());
            setPersonIntro(userRequest.getPersonIntro());
            setDeviceType(userRequest.getDeviceType());
            setRegisteredSource(userRequest.getRegisteredSource());
            setIpAddress(ipAddress);
            setIpSource(ipSource);
        }};
        //endregion
    }

}
