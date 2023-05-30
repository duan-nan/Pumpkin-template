package com.pumpkin.disease.constant;

/**
 * @author Pumpkin
 * @description 策略服务常量类
 * @date 2023/3/01
 */
public class StrategyServiceConstant {

    /**
     * 上传
     */
    public static class Upload{

        /**
         * ecsUploadStrategyImpl
         */
        public static final String PROJECT_UPLOAD_STRATEGY_SERVICE_NAME = "projectUploadStrategyImpl";

        /**
         * ossUploadStrategyImpl
         */
        public static final String OSS_UPLOAD_STRATEGY_SERVICE_NAME = "ossUploadStrategyImpl";

        /**
         * cosUploadStrategyImpl
         */
        public static final String COS_UPLOAD_STRATEGY_SERVICE_NAME = "cosUploadStrategyImpl";

        /**
         * kodoUploadStrategyImpl
         */
        public static final String KODO_UPLOAD_STRATEGY_SERVICE_NAME = "kodoUploadStrategyImpl";
    }

    /**
     * 邮箱
     */
    public static class EmailCaptcha {

        /**
         * loginEmailCaptchaServiceName
         */
        public static final String LOGIN_EMAIL_CAPTCHA_SERVICE_NAME = "loginEmailCaptchaServiceName";

        /**
         * registerEmailCaptchaServiceName
         */
        public static final String REGISTER_EMAIL_CAPTCHA_SERVICE_NAME = "registerEmailCaptchaServiceName";

        /**
         * changePasswordEmailCaptchaServiceName
         */
        public static final String CHANGE_PASSWORD_EMAIL_CAPTCHA_SERVICE_NAME = "changePasswordEmailCaptchaServiceName";

        /**
         * forgetPasswordEmailCaptchaServiceName
         */
        public static final String FORGET_PASSWORD_EMAIL_CAPTCHA_SERVICE_NAME = "forgetPasswordEmailCaptchaServiceName";

    }

}

