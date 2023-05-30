package com.pumpkin.disease.enums.log;


import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import com.pumpkin.disease.constant.SystemConstant.LogType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Pumpkin
 * @version 1.0
 * @description 操作类型枚举类
 * @date 2022/7/12
 */
@Getter
@AllArgsConstructor
public enum LogTypeEnum {

    /**
     * 新增数据
     */
    SAVE_OPERATE(0, LogType.SAVE_OPERATE),
    /**
     * 更新数据
     */
    UPDATE_OPERATE(1, LogType.UPDATE_OPERATE),
    /**
     * 删除数据
     */
    DELETE_OPERATE(2, LogType.DELETE_OPERATE),
    /**
     * 批量删除
     */
    BATCH_DELETE_OPERATE(3, LogType.BATCH_DELETE_OPERATE),
    /**
     * 导入数据
     */
    IMPORT_OPERATE(4, LogType.IMPORT_OPERATE),
    /**
     * 导出数据
     */
    EXPORT_OPERATE(5, LogType.EXPORT_OPERATE),
    /**
     * 授权
     */
    AUTHORIZATION_OPERATE(6, LogType.AUTHORIZATION_OPERATE),
    /**
     * 上传文件
     */
    UPLOAD_OPERATE(7, LogType.UPLOAD_OPERATE),
    /**
     * 审核
     */
    VERIFY_OPERATE(8, LogType.VERIFY_OPERATE),
    /**
     * 邮箱发送
     */
    SEND_EMAIL(9, LogType.SEND_EMAIL_OPERATE);

    /**
     * 类型码
     */
    private final Integer typeCode;

    /**
     * 类型名称
     */
    private final String typeName;

    private final static Map<String, LogTypeEnum> NAME_VALUE_MAP  = new HashMap<>((int) (LogTypeEnum.values().length / 0.75) + 1);
    private final static Map<Integer, LogTypeEnum> CODE_VALUE_MAP = new HashMap<>((int) (LogTypeEnum.values().length / 0.75) + 1);

    static {
        for (LogTypeEnum logTypeEnum : LogTypeEnum.values()) {
            NAME_VALUE_MAP.put(logTypeEnum.getTypeName(), logTypeEnum);
            CODE_VALUE_MAP.put(logTypeEnum.getTypeCode(), logTypeEnum);
        }
    }

    /**
     * 获取操作类型枚举信息
     *
     * @param typeCode 类型码
     * @return {@link LogTypeEnum} 日志枚举类型
     */
    public static LogTypeEnum getEnumByCode(Integer typeCode) {
        if (CODE_VALUE_MAP.containsKey(typeCode)) {
            return CODE_VALUE_MAP.get(typeCode);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }

    /**
     * 获取操作类型枚举信息
     *
     * @param typeCode 类型名称
     * @return {@link LogTypeEnum} 日志枚举类型
     */
    public static LogTypeEnum getEnumByName(String typeName) {
        if (NAME_VALUE_MAP.containsKey(typeName)) {
            return NAME_VALUE_MAP.get(typeName);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }



}
