package com.pumpkin.disease.enums.device;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import com.pumpkin.disease.response.system.enums.EnumResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Pumpkin
 * @description: 设备类型枚举类
 * @since : 2023/3/13
 */
@Getter
@AllArgsConstructor
public enum DeviceTypeEnum {

    /**
     * PC站
     */
    PC(1, "PC"),
    /**
     * 安卓
     */
    ANDROID(2, "安卓"),
    /**
     * 苹果
     */
    APPLE(3, "苹果");


    /**
     * 设备码
     */
    private final int deviceCode;

    /**
     * 设备名称
     */
    private final String deviceName;

    /**
     * 初始化map
     */
    private static final Map<Integer, DeviceTypeEnum> CODE_NAME_MAP = new HashMap<>((int) ((DeviceTypeEnum.values().length / 0.75) + 1));

    /**
     * 设备来源列表
     */
    public static final List<EnumResponse<Integer>> DEVICE_TYPE_ENUM_LIST = new ArrayList<>(DeviceTypeEnum.values().length);

    static {
        for (DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()) {
            DEVICE_TYPE_ENUM_LIST.add(new EnumResponse<Integer>(deviceTypeEnum.getDeviceCode(), deviceTypeEnum.deviceName));
            CODE_NAME_MAP.put(deviceTypeEnum.getDeviceCode(), deviceTypeEnum);
        }
    }

    /**
     * 根据code获取设备类型枚举
     *
     * @param deviceCode 设备码
     * @return {@link DeviceTypeEnum} 设备信息枚举类
     */
    public static DeviceTypeEnum getDeviceTypeByCode(Integer deviceCode) {
        if (CODE_NAME_MAP.containsKey(deviceCode)) {
            return CODE_NAME_MAP.get(deviceCode);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }

}
