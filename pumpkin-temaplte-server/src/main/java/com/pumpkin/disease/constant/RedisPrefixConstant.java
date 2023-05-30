package com.pumpkin.disease.constant;

/**
 * @author: Pumpkin
 * @description: Redis前缀常量
 * @since : 2023/3/8
 */
public class RedisPrefixConstant {


    /**
     * Redis-菜单前缀
     */
    public static class MenuPrefix {

        /**
         * 默认前缀
         */
        private static final String DEFAULT_PREFIX = "menuCache:";

        /**
         * 所有菜单列表（包含按钮类型）
         */
        public static final String ALL_MENU_LIST = DEFAULT_PREFIX + "allMenuList";

        /**
         * 当前角色所拥有菜单列表
         */
        public static final String CURRENT_ROLE_MENU_LIST = DEFAULT_PREFIX + "currentRoleMenuList:";

        /**
         * 不包含按钮类型的菜单列表
         */
        public static final String NO_BUTTON_MENU_LIST = DEFAULT_PREFIX + "noButtonMenuList";

        /**
         * 菜单详情信息
         */
        public static final String MENU_DETAIL_CACHE = DEFAULT_PREFIX + "menuDetail:";

        /**
         * 权限菜单列表
         */
        public static final String PERM_MENU_LIST = DEFAULT_PREFIX + "permMenuList";

        /**
         * 所有菜单id集合
         */
        public static final String ALL_MENU_ID_LIST = "allMenuIdList";


    }


    /**
     * Redis-角色前缀
     */
    public static class RolePrefix {

        /**
         * 默认前缀
         */
        private static final String DEFAULT_PREFIX = "role:";

    }

    /**
     * Redis-对象存储前缀
     */
    public static class ObjectStorePrefix {

        /**
         * 默认前缀
         */
        private static final String DEFAULT_PREFIX = "object_store:";

        /**
         * 基础信息列表
         */
        public static final String BASIC_INFO_LIST = DEFAULT_PREFIX + "basic_info_list";

        /**
         * 详细信息
         */
        public static final String DETAIL_INFO = "detail_info:";

    }

    /**
     * Redis-网站配置前缀
     */
    public static class WebsiteConfigPrefix {

        /**
         * 默认前缀
         */
        public static final String DEFAULT_PREFIX = "website_config:";

    }


    /**
     * Redis-邮箱验证码前缀
     */
    public static class EmailCaptchaPrefix {

        /**
         * 默认前缀
         */
        private static final String DEFAULT_PREFIX = "emailCaptcha:";

        /**
         * 登录验证码
         */
        public static final String LOGIN_CAPTCHA = DEFAULT_PREFIX + "loginCaptcha:";

        /**
         * 注册验证码
         */
        public static final String REGISTER_CAPTCHA = DEFAULT_PREFIX + "registerCaptcha:";

        /**
         * 修改密码验证码
         */
        public static final String CHANGE_PASSWORD_CAPTCHA = DEFAULT_PREFIX + "changePasswordCaptcha:";

        /**
         * 找回密码验证码
         */
        public static final String FORGET_PASSWORD_CAPTCHA = DEFAULT_PREFIX + "forgetPasswordCaptcha:";

    }
}
