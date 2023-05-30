package com.pumpkin.disease.strategy.context;

import com.pumpkin.disease.enums.email.EmailEnum;
import com.pumpkin.disease.request.security.email.EmailCaptchaRequest;
import com.pumpkin.disease.request.security.email.EmailRequest;
import com.pumpkin.disease.strategy.EmailStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: Pumpkin
 * @description: 邮箱验证码发送策略上下文
 * @since : 2023/4/23 13:36
 */
@Service
@AllArgsConstructor
public class EmailStrategyContext {

    private final Map<String, EmailStrategy> emailStrategyMap;

    /**
     * 执行验证码发送
     *
     * @param emailRequest 参数数据
     */
    public void executeSendEmailStrategy(final EmailRequest emailRequest) {
        EmailEnum emailEnum = EmailEnum.getStatusNameEnumByCode(emailRequest.getEmailCaptchaCode());
        emailStrategyMap.get(emailEnum.getEmailStrategy()).sendEmailToUser(emailRequest);
    }

    /**
     * 执行校验验证码
     *
     * @param emailCaptchaRequest 请求入参
     */
    public void executeCheckEmailStrategy(final EmailCaptchaRequest emailCaptchaRequest) {
        EmailEnum emailEnum = EmailEnum.getStatusNameEnumByCode(emailCaptchaRequest.getEmailCaptchaCode());
        emailStrategyMap.get(emailEnum.getEmailStrategy()).checkEmailCaptcha(emailCaptchaRequest);
    }
}
