package com.pumpkin.disease.utils.vaild;

import com.pumpkin.disease.base.exception.BaseException;

import java.util.List;

/**
 * @author: Pumpkin
 * @description: 校验工具类
 * @since : 2023/3/2
 */
public class AssertUtil {

    /**
     * 校验是否未通过
     *
     * @param isPass       true:未通过 false:通过
     * @param errorMessage 错误信息
     */
    public static void assertIsNotPass(boolean isPass, String errorMessage) {
        if (isPass) {
            throw new BaseException(errorMessage);
        }
    }

    /**
     * 校验字符串是否为空
     *
     * @param str 字符串
     * @return true:为空 false:不为空
     */
    public static boolean isEmpty(String str) {
        return null == str || str.trim().isEmpty();
    }

    /**
     * 校验字符串是否不为空
     *
     * @param str 字符串
     * @return true:不为空 false:为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 校验对象是否为null
     *
     * @param o 对象
     * @return true:为null false:不为null
     */
    public static boolean isNull(Object o) {
        return null == o || "".equals(o);
    }

    /**
     * 校验对象是否不为null
     *
     * @param o 对象
     * @return true:不为null false:为null
     */
    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * 是否新增成功
     *
     * @param count count
     * @return true:新增成功  false:新增失败
     */
    public static boolean isSaveSuccess(Integer count) {
        return count > 0;
    }

    /**
     * 是否新增失败
     *
     * @param count count
     * @return true:新增成功  false:新增失败
     */
    public static boolean isSaveFail(Integer count) {
        return !isSaveSuccess(count);
    }


    /**
     * 是否更新一条数据成功
     *
     * @param count count
     * @return true:新增成功  false:新增失败
     */
    public static boolean isUpdateOneSuccess(Integer count) {
        return count > 0;
    }

    /**
     * 是否更新一条数据失败
     *
     * @param count count
     * @return true:新增成功  false:新增失败
     */
    public static boolean isUpdateOneFail(Integer count) {
        return !isUpdateOneSuccess(count);
    }

    /**
     * list集合为空
     * @param list list集合
     * @return true 集合为空 false 集合不为空
     */
    public static <T> boolean isListEmpty(List<T> list){
        return list.isEmpty();
    }

    /**
     * list集合不为空
     * @param list list集合
     * @return true 集合不为空 false 集合为空
     */
    public static boolean isListNotEmpty(List<Object> list){
        return !isListEmpty(list);
    }
}
