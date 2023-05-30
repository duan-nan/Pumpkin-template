package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.system.website.ObjectStoreConditionRequest;
import com.pumpkin.disease.request.system.website.ObjectStoreRequest;
import com.pumpkin.disease.request.system.website.ObjectStoreStatusRequest;
import com.pumpkin.disease.response.system.website.ObjectStoreBasicInfoResponse;
import com.pumpkin.disease.response.system.website.ObjectStoreResponse;
import com.pumpkin.disease.response.system.website.ObjectStoreSelectResponse;
import com.pumpkin.disease.service.ObjectStoreService;
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
 * 对象存储表 前端控制器
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "对象存储模块")
@RequestMapping("/object-store")
public class ObjectStoreController {

    private final ObjectStoreService objectStoreService;

    /**
     * 查询对象存储信息
     *
     * @param objectStoreConditionRequest 请求入参
     * @return {@link ObjectStoreResponse}
     */
    @ApiOperation(value = "[后台]查询对象存储信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:query')")
    @PostMapping("/list")
    public ResponseResult<PageResult<ObjectStoreResponse>> listObjectStore(@RequestBody ObjectStoreConditionRequest objectStoreConditionRequest) {
        return ResponseResult.success("查询对象存储数据成功！", objectStoreService.listObjectStore(objectStoreConditionRequest));
    }

    /**
     * 查询对象存储基础信息
     *
     * @return {@link  ObjectStoreBasicInfoResponse} 对象存储基础信息
     */
    @ApiOperation(value = "[后台]查询对象存储基础信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:query')")
    @GetMapping("/list/basic/info")
    public ResponseResult<List<ObjectStoreBasicInfoResponse>> listObjectStoreBasicInfo() {
        return ResponseResult.success("查询对象存储基础信息成功!", objectStoreService.listObjectStoreBasicInfo());
    }

    /**
     * 查询对象存储下拉框信息
     *
     * @return {@link  ObjectStoreSelectResponse} 对象存储下拉框信息
     */
    @ApiOperation(value = "[后台]查询对象存储下拉框信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:list')")
    @GetMapping("/list/select")
    public ResponseResult<List<ObjectStoreSelectResponse>> listObjectStoreSelect() {
        return ResponseResult.success("查询对象存储下拉框信息成功!", objectStoreService.listObjectStoreSelect());
    }

    /**
     * 查询对象存储信息
     *
     * @param objectStoreId 对象存储id
     * @return {@link ObjectStoreResponse} 对象存储对象
     */
    @ApiOperation(value = "[后台]查询对象存储详细信息")
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:get')")
    @PostMapping("/get/{objectStoreId}")
    public ResponseResult<ObjectStoreResponse> getObjectStoreInfo(@PathVariable Integer objectStoreId) {
        return ResponseResult.success("查询对象存储信息成功！", objectStoreService.getObjectStoreInfo(objectStoreId));
    }


    /**
     * 新增对象存储信息
     *
     * @param objectStoreRequest 对象存储请求入参
     * @return 是否新增成功
     */
    @ApiOperation(value = "[后台]新增对象存储信息")
    @NormalOperateLog(operateType = SAVE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:save')")
    @PostMapping("/save")
    public ResponseResult<?> saveObjectStore(@RequestBody @Valid ObjectStoreRequest objectStoreRequest) {
        objectStoreService.saveObjectStore(objectStoreRequest);
        return ResponseResult.success("新增对象存储信息成功！");
    }


    /**
     * 修改对象存储信息
     *
     * @param objectStoreRequest 对象存储请求入参
     * @return 是否修改成功
     */
    @ApiOperation(value = "[后台]修改对象存储信息")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:update')")
    @PutMapping("/update")
    public ResponseResult<?> updateObjectStore(@RequestBody @Valid ObjectStoreRequest objectStoreRequest) {
        objectStoreService.updateObjectStore(objectStoreRequest);
        return ResponseResult.success("修改对象存储信息成功！");
    }


    /**
     * 更新对象存储状态
     *
     * @param objectStoreStatusRequest 对象存储请求入参
     * @return 状态是否修改成功
     */
    @ApiOperation(value = "[后台]修改对象存储状态")
    @NormalOperateLog(operateType = UPDATE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:update')")
    @PutMapping("/updateStatus")
    public ResponseResult<?> updateObjectStore(@RequestBody ObjectStoreStatusRequest objectStoreStatusRequest) {
        objectStoreService.updateObjectStoreStatus(objectStoreStatusRequest);
        return ResponseResult.success("更新对象存储状态成功！");
    }


    /**
     * 批量删除对象存储信息
     *
     * @param idRequest id集合
     * @return 是否删除成功
     */
    @ApiOperation(value = "[后台]批量删除对象存储信息")
    @NormalOperateLog(operateType = BATCH_DELETE_OPERATE)
    @PreAuthorize("@mznPerms.checkCurrentUserIsHasPerm('system:objectStore:delete')")
    @DeleteMapping("/batch/delete")
    public ResponseResult<?> deleteObjectStore(@RequestBody IDRequest<Long> idRequest) {
        objectStoreService.deleteObjectStore(idRequest);
        return ResponseResult.success("删除对象存储信息成功！");
    }
}