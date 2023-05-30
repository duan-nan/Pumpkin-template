package com.pumpkin.disease.enums.source;

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
 * @description: 注册来源枚举类
 * @since : 2023/3/13
 */
@Getter
@AllArgsConstructor
public enum RegisteredSourceEnum {

    /**
     * PC站
     */
    PC(1, "PC"),
    /**
     * 小程序
     */
    SMALL_PROGRAM(2, "小程序"),
    /**
     * App
     */
    APP(3, "app");

    /**
     * 注册来源编码
     */
    private final int registeredSourceCode;

    /**
     * 注册来源名称
     */
    private final String registeredSourceName;


    /**
     * 初始化map
     */
    private static final Map<Integer, RegisteredSourceEnum> CODE_NAME_MAP = new HashMap<>((int) ((RegisteredSourceEnum.values().length / 0.75) + 1));

    /**
     * 注册来源枚举类
     */
    public static final List<EnumResponse<Integer>> REGISTERED_SOURCE_ENUM_LIST = new ArrayList<>(RegisteredSourceEnum.values().length);

    static {
        for (RegisteredSourceEnum registeredSourceEnum : RegisteredSourceEnum.values()) {
            REGISTERED_SOURCE_ENUM_LIST.add(new EnumResponse<Integer>(registeredSourceEnum.registeredSourceCode, registeredSourceEnum.registeredSourceName));
            CODE_NAME_MAP.put(registeredSourceEnum.getRegisteredSourceCode(), registeredSourceEnum);
        }
    }

    /**
     * 根据code获取注册来源枚举
     *
     * @param registeredSourceCode 注册来源码
     * @return {@link RegisteredSourceEnum} 注册来源枚举类
     */
    public static RegisteredSourceEnum getRegisteredSourceEnumByCode(Integer registeredSourceCode) {
        if (CODE_NAME_MAP.containsKey(registeredSourceCode)) {
            return CODE_NAME_MAP.get(registeredSourceCode);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }
}
