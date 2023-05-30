package com.pumpkin.disease.controller.security;

import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.entity.Menu;
import com.pumpkin.disease.request.security.menu.MenuRequest;
import com.pumpkin.disease.response.security.menu.MenuDetailResponse;
import com.pumpkin.disease.response.security.menu.MenuPermResponse;
import com.pumpkin.disease.service.MenuService;
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
 * 菜单表 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@Api(tags = "菜单模块")
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * [后台]查询后台菜单列表
     *
     * @param isSelectButton 是否只查询菜单
     * @return {@link Menu} 菜单信息
     */
    @ApiOperation(value = "[后台]查询后台菜单列表")
    @GetMapping("/list/tree")
    public ResponseResult<List<Menu>> listMenu(@RequestParam Boolean isSelectButton) {
        return ResponseResult.success("查询菜单列表成功！", menuService.listMenu(isSelectButton));
    }

    /**
     * 查询指定菜单信息
     *
     * @param menuId 菜单id
     * @return {@link  MenuDetailResponse} 菜单
     */
    @ApiOperation(value = "[后台]查询指定菜单信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:menu:get')")
    @GetMapping("/get/{menuId}")
    public ResponseResult<MenuDetailResponse> getMenuById(@PathVariable Long menuId) {
        return ResponseResult.success("查询菜单信息成功！", menuService.getMenuById(menuId));
    }

    /**
     * 查询菜单权限列表
     * @return {@link MenuPermResponse} 菜单权限列表
     */
    @ApiOperation(value = "[后台]查询菜单权限列表")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:menu:list')")
    @GetMapping("/list/perm")
    public ResponseResult<List<MenuPermResponse>> listMenuPerm(){
        return ResponseResult.success("查询菜单权限列表成功！",menuService.listMenuPerm());
    }

    /**
     * 查询所有菜单id列表
     * @return 所有菜单id集合
     */
    @ApiOperation(value = "[后台]查询所有菜单id列表")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:menu:list')")
    @GetMapping("/list/allIds")
    public ResponseResult<List<Long>> listAllIds(){
        return ResponseResult.success("查询菜单id集合成功！",menuService.listAllIds());
    }

    /**
     * 新增菜单信息
     *
     * @param menuRequest 菜单请求入参
     * @return 是否新增成功
     */
    @ApiOperation(value = "[后台]新增菜单信息")
    @NormalOperateLog(operateType = SAVE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:menu:save')")
    @PostMapping("/save")
    public ResponseResult<?> saveMenu(@RequestBody @Valid MenuRequest menuRequest) {
        menuService.saveMenu(menuRequest);
        return ResponseResult.success("新增菜单信息成功！");
    }

    /**
     * 更新菜单信息
     *
     * @param menuRequest 菜单请求入参
     * @return 是否更新成功
     */
    @ApiOperation(value = "[后台]更新菜单信息")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:menu:update')")
    @PostMapping("/update")
    public ResponseResult<?> updateMenu(@RequestBody @Valid MenuRequest menuRequest) {
        menuService.updateMenu(menuRequest);
        return ResponseResult.success("更新菜单信息成功！");
    }

    /**
     * 删除菜单信息
     *
     * @param idRequest 请求入参
     * @return 菜单是否删除成功
     */
    @ApiOperation(value = "[后台]删除菜单信息")
    @NormalOperateLog(operateType = DELETE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('security:menu:delete')")
    @DeleteMapping("/delete")
    public ResponseResult<?> deleteMenu(@RequestBody IDRequest<Long> idRequest) {
        menuService.deleteMenu(idRequest);
        return ResponseResult.success("删除菜单成功！");
    }
}
