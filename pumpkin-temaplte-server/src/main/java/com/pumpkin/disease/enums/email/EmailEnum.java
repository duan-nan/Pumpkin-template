package com.pumpkin.disease.enums.email;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.pumpkin.disease.constant.StrategyServiceConstant.EmailCaptcha.*;

/**
 * @author: Pumpkin
 * @description: 邮箱验证码枚举类
 * @since : 2023/4/23 13:46
 */
@Getter
@AllArgsConstructor
public enum EmailEnum {

    /**
     * 用户登录
     */
    LOGIN_EMAIL_CAPTCHA(1, LOGIN_EMAIL_CAPTCHA_SERVICE_NAME, "用户登录"),
    /**
     * 注册账户
     */
    REGISTER_EMAIL_CAPTCHA(2, REGISTER_EMAIL_CAPTCHA_SERVICE_NAME, "用户注册"),
    /**
     * 用户修改账户邮箱
     */
    CHANGE_PASSWORD_EMAIL_CAPTCHA(3, CHANGE_PASSWORD_EMAIL_CAPTCHA_SERVICE_NAME, "邮箱换绑"),
    /**
     * 用户忘记密码
     */
    FORGET_PASSWORD_EMAIL_CAPTCHA(4, FORGET_PASSWORD_EMAIL_CAPTCHA_SERVICE_NAME, "忘记密码");

    /**
     * 邮箱验证码编号
     */
    private final Integer emailCode;

    /**
     * 邮箱验证码策略
     */
    private final String emailStrategy;

    /**
     * 邮箱验证码信息
     */
    private final String emailMessage;

    /**
     * 初始化map
     */
    private static final Map<Integer, EmailEnum> CODE_NAME_MAP = new HashMap<>((int) ((EmailEnum.values().length / 0.75) + 1));

    static {
        for (EmailEnum emailEnum : EmailEnum.values()) {
            CODE_NAME_MAP.put(emailEnum.getEmailCode(), emailEnum);
        }
    }


    /**
     * 根据code获取状态名称
     *
     * @param emailCode 邮箱验证码编号
     * @return {@link EmailEnum} 邮箱验证码信息
     */
    public static EmailEnum getStatusNameEnumByCode(Integer emailCode) {
        if (CODE_NAME_MAP.containsKey(emailCode)) {
            return CODE_NAME_MAP.get(emailCode);
        } else {
            throw new BaseException(BusinessErrorConstant.EnumError.NO_ENUM_FOUNDED_BY_KEY);
        }
    }
}
