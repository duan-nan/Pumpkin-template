package com.pumpkin.disease.base.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Pumpkin
 * @version 1.0
 * @description 分页结果数据
 * @date 2022/11/24
 */
@Data
@AllArgsConstructor
public class PageResult<T> {

    /**
     * 总数据量
     */
    private long totalCount;

    /**
     * 数据列表
     */
    private List<T> dataList;

}