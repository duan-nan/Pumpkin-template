package com.pumpkin.disease.controller.security;

import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.security.user.ImageRequest;
import com.pumpkin.disease.request.security.user.UserInfoRequest;
import com.pumpkin.disease.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static com.pumpkin.disease.constant.SystemConstant.LogType.UPDATE_OPERATE;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@Api(tags = {"用户信息模块"})
@RequiredArgsConstructor
@RequestMapping("/user-info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * 获取用户信息
     *
     * @param principal Principal
     * @return 用户信息
     */
    @ApiOperation(value = "[通用]获取用户信息")
    @GetMapping("/getMy")
    public ResponseResult<?> getCurrentUserInfo(Principal principal) {
        return ResponseResult.success("查询用户信息成功！", principal);
    }

    /**
     * 更新用户信息
     *
     * @param userInfoRequest 用户请求入参
     * @return 是否更新成功
     */
    @ApiOperation(value = "[后台]更新用户信息")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:userInfo:update')")
    @PostMapping("/update/info")
    public ResponseResult<?> updateUserInfo(@RequestBody @Valid UserInfoRequest userInfoRequest) {
        userInfoService.updateUserInfo(userInfoRequest);
        return ResponseResult.success("更新用户信息成功！");
    }

    /**
     * 更新用户头像
     *
     * @param imageRequest 用户头像url
     * @return 是否更新成功
     */
    @ApiOperation(value = "[后台]更新用户头像")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PostMapping("/update/avatar")
    public ResponseResult<?> updateUserAvatar(@RequestBody @Valid ImageRequest imageRequest) {
        userInfoService.updateUserAvatar(imageRequest.getImageUrl());
        return ResponseResult.success("更新用户头像成功！");
    }


}
