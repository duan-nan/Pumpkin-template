package com.pumpkin.disease.base.request;

import lombok.Getter;

import java.util.List;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/17
 * @description: id集合请求入参
 * @version： 1.0
 */
@Getter
public class IDRequest<T> {

    /**
     * id集合
     */
    private List<T> ids;

    /**
     * id
     */
    private T id;

}
