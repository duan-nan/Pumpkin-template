package com.pumpkin.disease.response.system.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Pumpkin
 * @description: 枚举返回值信息
 * @since : 2023/3/15
 */
@Data
@AllArgsConstructor
public class EnumResponse<T> {

    /**
     * code编码
     */
    private T value;

    /**
     * 枚举名称
     */
    private String name;

}
