package com.pumpkin.disease.service;

/**
 *
 * @author pumpkin
 * @description 邮箱服务接口
 * @date 2022/4/23
 */
public interface EmailService {

    /**
     * 使用邮箱模版给用户发送邮箱
     * @param receiver 接受者
     * @param emailSubject 邮箱主题
     * @param emailTypeMessage 邮箱类型信息
     * @param templatePath 邮箱模版路径
     * @param emailCaptcha 邮箱验证码
     */
    void sendEmailToUserByEmailTemplate(String receiver,String emailSubject,String emailTypeMessage,String templatePath,String emailCaptcha);

}
