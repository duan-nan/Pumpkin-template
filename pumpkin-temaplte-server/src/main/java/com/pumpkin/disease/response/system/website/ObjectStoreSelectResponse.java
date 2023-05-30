package com.pumpkin.disease.response.system.website;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Pumpkin
 * @description: 对象存储下拉框 Response
 * @since : 2023/3/6
 */
@Data
@AllArgsConstructor
public class ObjectStoreSelectResponse {

    /**
     * 对象存储名称
     */
    private String name;

    /**
     * 对象存储标识
     */
    private String label;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    public ObjectStoreSelectResponse(String name, String label) {
        this.name = name;
        this.label = label;
    }
}
