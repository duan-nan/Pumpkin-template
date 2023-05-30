package com.pumpkin.disease.controller.security;

import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.security.role.RolePageRequest;
import com.pumpkin.disease.request.security.role.RolePermRequest;
import com.pumpkin.disease.request.security.role.RoleRequest;
import com.pumpkin.disease.response.security.role.RoleBasicResponse;
import com.pumpkin.disease.response.security.role.RoleMenuResponse;
import com.pumpkin.disease.response.security.role.RoleResponse;
import com.pumpkin.disease.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.pumpkin.disease.constant.SystemConstant.LogType.*;

/**
 * <p>
 * 角色表信息 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@Api(tags = "角色模块")
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 分页查询角色列表
     *
     * @param rolePageRequest 角色请求入参
     * @return {@link  RoleResponse} 角色信息列表
     */
    @ApiOperation(value = "[后台]分页查询角色列表")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:role:query')")
    @PostMapping("/list")
    public ResponseResult<PageResult<RoleResponse>> listRole(@RequestBody @Valid RolePageRequest rolePageRequest) {
        return ResponseResult.success("查询角色列表成功！", roleService.listRole(rolePageRequest));
    }

    /**
     * 获取指定角色详细信息
     * @param roleId 角色id
     * @return {@link RoleMenuResponse} 角色信息
     */
    @ApiOperation(value = "[后台]查询角色详细信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:role:get')")
    @GetMapping("/get/{roleId}")
    public ResponseResult<RoleMenuResponse> getRoleByRoleId(@PathVariable String roleId){
        return ResponseResult.success("查询角色信息成功！",roleService.getRoleByRoleId(roleId));
    }

    /**
     * 查询角色基础信息列表
     * @return {@link RoleBasicResponse} 角色信息
     */
    @ApiOperation(value = "[后台]查询角色基础信息列表")
    @GetMapping("/list/basic")
    public ResponseResult<List<RoleBasicResponse>> listBasicRole(){
        return ResponseResult.success("查询角色基础信息成功！",roleService.listBasicRole());
    }


    /**
     * 新增角色信息
     * @param request 角色请求入参
     * @return 是否新增成功
     */
    @ApiOperation(value = "[后台]新增角色信息")
    @NormalOperateLog(operateType = SAVE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:role:save')")
    @PostMapping("/save")
    public ResponseResult<?> saveRole(@RequestBody @Valid RoleRequest request){
        roleService.saveRole(request);
        return ResponseResult.success("新增角色信息成功！");
    }

    /**
     * 更新角色信息
     * @param request 角色请求入参
     * @return 是否更新成功
     */
    @ApiOperation(value = "[后台]更新角色信息")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:role:update')")
    @PostMapping("/update")
    public ResponseResult<?> updateRole(@RequestBody @Valid RoleRequest request){
        roleService.updateRole(request);
        return ResponseResult.success("更新角色信息成功！");
    }

    /**
     * 更新角色权限列表
     * @param rolePermRequest 请求入参
     * @return 是否更新成功
     */
    @ApiOperation(value = "[后台]更新角色权限列表")
    @NormalOperateLog(operateType = AUTHORIZATION_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:role:authorization')")
    @PostMapping("/update/perm")
    public ResponseResult<?> updatePerm(@RequestBody @Valid RolePermRequest rolePermRequest){
        roleService.updatePerm(rolePermRequest);
        return ResponseResult.success("更新角色权限成功！");
    }

    /**
     * 删除角色信息
     * @param idRequest 请求入参
     * @return 是否删除成功
     */
    @ApiOperation(value = "[后台]删除角色信息")
    @NormalOperateLog(operateType = DELETE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:role:delete')")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteRole(@RequestBody IDRequest<String> idRequest){
        roleService.deleteRole(idRequest);
        return ResponseResult.success("删除角色信息成功！");
    }

}
