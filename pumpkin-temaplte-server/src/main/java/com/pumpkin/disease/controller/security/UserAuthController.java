package com.pumpkin.disease.controller.security;

import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.security.user.PasswordRequest;
import com.pumpkin.disease.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户权限表 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@Api(tags = {"用户权限模块"})
@RequiredArgsConstructor
@RequestMapping("/user-auth")
public class UserAuthController {

    private final UserAuthService userAuthService;

    /**
     * 更新用户密码
     *
     * @param passwordRequest 用户请求入参
     * @return 是否更新成功
     */
    @ApiOperation(value = "[后台]更新用户密码")
    @PostMapping("/update/password")
    public ResponseResult<?> updateUserPassword(@RequestBody @Valid PasswordRequest passwordRequest) {
        userAuthService.updateUserPassword(passwordRequest);
        return ResponseResult.success("更新用户密码成功！");
    }

}
