package com.pumpkin.disease.controller.security;

import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.security.user.UserConditionRequest;
import com.pumpkin.disease.request.security.user.UserRequest;
import com.pumpkin.disease.response.security.user.UserResponse;
import com.pumpkin.disease.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.pumpkin.disease.constant.SystemConstant.LogType.*;

/**
 * @author: Pumpkin
 * @description: 用户信息 前端控制器
 * @since : 2023/3/13
 */
@RestController
@Api(tags = {"用户模块"})
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 查询用户列表
     *
     * @param userConditionRequest 用户条件请求入参
     * @return {@link  UserResponse} 用户响应信息
     */
    @ApiOperation(value = "[后台]查询用户列表")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:user:query')")
    @PostMapping("/list")
    public ResponseResult<PageResult<UserResponse>> queryUserList(@RequestBody @Valid UserConditionRequest userConditionRequest) {
        return ResponseResult.success("查询用户列表信息成功！", userService.queryUserList(userConditionRequest));
    }

    /**
     * 新增用户信息
     *
     * @param userRequest 请求入参
     * @return 是否新增成功
     */
    @ApiOperation(value = "[后台]新增用户信息")
    @NormalOperateLog(operateType = SAVE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:user:save')")
    @PostMapping("/save")
    public ResponseResult<?> saveUser(@RequestBody @Valid UserRequest userRequest) {
        userService.saveUser(userRequest);
        return ResponseResult.success("新增用户成功！");
    }

    /**
     * 更新用户信息
     *
     * @param userRequest 请求入参
     * @return 是否更新成功
     */
    @ApiOperation(value = "[后台]更新用户信息")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:user:update')")
    @PostMapping("/update")
    public ResponseResult<?> updateUser(@RequestBody UserRequest userRequest) {
        userService.updateUser(userRequest);
        return ResponseResult.success("更新用户信息成功！");
    }

    /**
     * 重置密码
     * @param id 用户id
     * @param password 密码
     * @return 是否重置成功
     */
    @ApiOperation(value = "[后台]重置密码")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:user:updatePassword')")
    @PostMapping("/reset/password/{id}")
    public ResponseResult<?> resetUserPassword(@RequestBody String password, @PathVariable String id) {
        userService.resetUserPassword(id, password);
        return ResponseResult.success("重置用户密码成功！");
    }

    /**
     * 批量删除用户信息
     *
     * @param idRequest id请求入参
     * @return 是否删除成功
     */
    @ApiOperation(value = "[后台]删除用户信息")
    @NormalOperateLog(operateType = BATCH_DELETE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:user:delete')")
    @DeleteMapping("/batch/delete")
    public ResponseResult<?> batchDeleteUser(@RequestBody IDRequest<String> idRequest) {
        userService.batchDeleteUser(idRequest);
        return ResponseResult.success("删除用户成功！");
    }

}
