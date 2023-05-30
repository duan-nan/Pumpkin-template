package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.enums.device.DeviceTypeEnum;
import com.pumpkin.disease.enums.menu.MenuRouteTypeEnum;
import com.pumpkin.disease.enums.source.RegisteredSourceEnum;
import com.pumpkin.disease.response.system.enums.EnumResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Pumpkin
 * @description: 枚举类控制器
 * @since : 2023/3/15
 */
@RestController
@Api(tags = {"枚举模块"})
@RequestMapping("/enum")
public class EnumController {

    /**
     * 设备来源枚举类
     * @return {@link EnumResponse} 枚举返回值信息
     */
    @ApiOperation(value = "[后台]设备来源枚举类")
    @GetMapping("/device/type/list")
    public ResponseResult<List<EnumResponse<Integer>>> listDeviceType() {
        return ResponseResult.success("查询设备来源列表成功！", DeviceTypeEnum.DEVICE_TYPE_ENUM_LIST);
    }

    /**
     * 注册来源枚举类
     * @return {@link EnumResponse} 枚举返回值信息
     */
    @ApiOperation(value = "[后台]注册来源枚举类")
    @GetMapping("/registered/source/list")
    public ResponseResult<List<EnumResponse<Integer>>> listRegisteredSource() {
        return ResponseResult.success("查询注册来源列表成功！", RegisteredSourceEnum.REGISTERED_SOURCE_ENUM_LIST);
    }

    /**
     * 路由菜单类型枚举
     *
     * @return {@link MenuRouteTypeEnum} 路由菜单枚举
     */
    @ApiOperation(value = "[后台]评论类型枚举类")
    @GetMapping("/menu/route/list")
    public ResponseResult<List<EnumResponse<Integer>>> listMenuRouteType() {
        return ResponseResult.success("查询评论类型列表成功！", MenuRouteTypeEnum.MENU_ROUTE_TYPE_ENUM_LIST);
    }

}
