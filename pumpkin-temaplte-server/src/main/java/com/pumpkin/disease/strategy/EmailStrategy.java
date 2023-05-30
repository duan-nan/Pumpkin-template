package com.pumpkin.disease.strategy;

import com.pumpkin.disease.request.security.email.EmailCaptchaRequest;
import com.pumpkin.disease.request.security.email.EmailRequest;

/**
 * @author: Pumpkin
 * @description: 邮箱验证码策略接口
 * @since : 2023/4/23 13:36
 */
public interface EmailStrategy {

    /**
     * 发送邮箱验证码
     * @param emailRequest 邮箱参数
     */
    void sendEmailToUser(final EmailRequest emailRequest);

    /**
     * 邮箱验证码校验
     * @param emailCaptchaRequest 邮箱验证码参数
     */
    void checkEmailCaptcha(final EmailCaptchaRequest emailCaptchaRequest);

}
