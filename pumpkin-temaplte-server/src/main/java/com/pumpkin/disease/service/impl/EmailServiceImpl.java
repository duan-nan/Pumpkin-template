package com.pumpkin.disease.service.impl;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.pumpkin.disease.constant.BusinessErrorConstant.EmailCaptchaError.EMAIL_SEND_FAIL;


/**
 * @author pumpkin
 * @description 邮箱服务类
 * @date 2022/4/23
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Async
    @Override
    public void sendEmailToUserByEmailTemplate(String receiver, String emailSubject, String emailTypeMessage, String templatePath, String emailCaptcha) {

        try {
            //region 实例化邮箱
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //endregion

            //region 发送接受人信息设置
            helper.setFrom(username);
            helper.setTo(receiver);
            helper.setSubject(emailSubject);
            //endregion

            //region 引用thymeleaf模版
            Context context = new Context();
            context.setVariable("captcha", emailCaptcha);
            context.setVariable("receiver", receiver);
            context.setVariable("emailMessage", emailTypeMessage);
            String emailContent = templateEngine.process(templatePath, context);
            helper.setText(emailContent, true);
            //endregion

            // 发送邮箱
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new BaseException(EMAIL_SEND_FAIL);
        }

    }
}
