package com.pumpkin.disease.enums.menu;

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
 * @description: 菜单路由枚举类
 * @since : 2023/4/14 11:00
 */
@Getter
@AllArgsConstructor
public enum MenuRouteTypeEnum {

    /**
     * 所有菜单
     */
    ALL_MENU_ROUTE(1,"所有菜单"),
    /**
     * 登录用户拥有菜单
     */
    CURRENT_USER_HAVE_MENU_ROUTE(2,"登录用户拥有菜单");

    /**
     * 状态码
     */
    private final int typeCode;

    /**
     * 状态名称
     */
    private final String typeName;


    /**
     * 初始化map
     */
    private static final Map<Integer, MenuRouteTypeEnum> CODE_NAME_MAP = new HashMap<>((int) ((MenuRouteTypeEnum.values().length / 0.75) + 1));

    /**
     * 路由菜单状态枚举类
     */
    public static final List<EnumResponse<Integer>> MENU_ROUTE_TYPE_ENUM_LIST = new ArrayList<>(MenuRouteTypeEnum.values().length);

    static {
        for (MenuRouteTypeEnum menuRouteTypeEnum : MenuRouteTypeEnum.values()) {
            MENU_ROUTE_TYPE_ENUM_LIST.add(new EnumResponse<Integer>(menuRouteTypeEnum.getTypeCode(),menuRouteTypeEnum.getTypeName()));
            CODE_NAME_MAP.put(menuRouteTypeEnum.getTypeCode(), menuRouteTypeEnum);
        }
    }

    /**
     * 根据code获取状态名称
     *
     * @param typeCode 状态码
     * @return {@link MenuRouteTypeEnum} 路由菜单类型枚举值
     */
    public static MenuRouteTypeEnum getTypeNameEnumByCode(Integer typeCode) {
        if (CODE_NAME_MAP.containsKey(typeCode)) {
            return CODE_NAME_MAP.get(typeCode);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }

}
