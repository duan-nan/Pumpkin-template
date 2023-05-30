package com.pumpkin.disease.enums.file;

import static com.pumpkin.disease.constant.StrategyServiceConstant.*;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import com.pumpkin.disease.enums.log.LogTypeEnum;
import com.pumpkin.disease.response.system.website.ObjectStoreSelectResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * @author Pumpkin
 * @description 上传模式枚举
 * @date 2023/3/01
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {

    /**
     * 服务器上传
     */
    PROJECT_UPLOAD("Project", Upload.PROJECT_UPLOAD_STRATEGY_SERVICE_NAME),
    /**
     * Oss上传(阿里云)
     */
    OSS_UPLOAD("Oss", Upload.OSS_UPLOAD_STRATEGY_SERVICE_NAME),
    /**
     * Cos上传(腾讯云)
     */
    COS_UPLOAD("Cos", Upload.COS_UPLOAD_STRATEGY_SERVICE_NAME),
    /**
     * Kodo上传（七牛云）
     */
    KODO_UPLOAD("Kodo", Upload.KODO_UPLOAD_STRATEGY_SERVICE_NAME);

    /**
     * 模式
     */
    private final String mode;

    /**
     * 策略
     */
    private final String strategy;


    private static final Map<String, UploadModeEnum> MODE_STRATEGY_MAP = new HashMap<>((int) (UploadModeEnum.values().length / 0.75) + 1);
    public static final List<ObjectStoreSelectResponse> OBJECT_STORE_SELECT_RESPONSE_LIST  = new ArrayList<>(UploadModeEnum.values().length);


    static {
        for (UploadModeEnum uploadModeEnum : UploadModeEnum.values()) {
            if (uploadModeEnum != PROJECT_UPLOAD) {
                OBJECT_STORE_SELECT_RESPONSE_LIST.add(new ObjectStoreSelectResponse(uploadModeEnum.getMode(), uploadModeEnum.getMode()));

            }
            MODE_STRATEGY_MAP.put(uploadModeEnum.getMode(), uploadModeEnum);
        }
    }

    /**
     * 获取上传枚举值
     *
     * @param mode 模式名称
     * @return {@link LogTypeEnum} 枚举类型
     */
    public static UploadModeEnum getEnumByMode(String mode) {
        if (MODE_STRATEGY_MAP.containsKey(mode)) {
            return MODE_STRATEGY_MAP.get(mode);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }


}
