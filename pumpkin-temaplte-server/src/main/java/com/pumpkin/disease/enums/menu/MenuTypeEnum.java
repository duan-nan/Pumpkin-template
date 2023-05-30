package com.pumpkin.disease.enums.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/25
 * @description: 菜单类型枚举类
 * @version： 1.0
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 一级菜单
     */
    MENU("一级菜单","M"),
    /**
     * 目录
     */
    CONTENT("目录","C"),
    /**
     * 按钮
     */
    BUTTON("按钮","B");


    /**
     * 名称
     */
    private final String name;

    /**
     * 编码
     */
    private final String code;

}
