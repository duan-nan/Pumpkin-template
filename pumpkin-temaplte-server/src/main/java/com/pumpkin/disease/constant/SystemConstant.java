package com.pumpkin.disease.constant;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/4
 * @description: 编码格式常量类
 * @version： 1.0
 */
public class SystemConstant {

    /**
     * 文件上传
     */
    public static class FileUpload {

        /**
         * 项目文件本地上传路径
         */
        public static final String FILE_PROJECT_UPLOAD_PATH = "\\target\\classes\\static\\store\\";

        /**
         * 项目资源路径
         */
        public static final String FILE_RESOURCE_PATH = "\\src\\main\\resources\\static\\store\\";

        /**
         * 系统存储位置
         */
        public static final String SYSTEM_STORE_PATH = "/store";

    }

    /**
     * 权限接口定义
     */
    public static class SecurityInterface {

        /**
         * 登录接口
         */
        public static final String LOGIN_INTERFACE = "/user/login";

        /**
         * 注销接口
         */
        public static final String LOGOUT_INTERFACE = "/user/logout";

    }

    /**
     * 编码常量
     */
    public static class Charset {

        /**
         * 编码格式设置
         */
        public static final String JSON_TYPE_UTF8_CHARSET = "application/json;charset=UTF-8";

    }

    /**
     * 数字常量
     */
    public static class Number {

        /**
         * 父级ID
         */
        public static final Long PARENT_DEFAULT_ID = 0L;

        /**
         * 结果数量
         */
        public static final Integer RESULT_NUMBER = 0;


        /**
         * 已经逻辑删除
         */
        public static final Integer IS_DELETED = 1;

        /**
         * 未逻辑删除
         */
        public static final Integer NOT_DELETED = 0;

        /**
         * 已经被禁用
         */
        public static final Integer IS_DISABLED = 1;

        /**
         * 没有被禁用
         */
        public static final Integer NOT_DISABLED = 0;
    }

    /**
     * 权限相关常量
     */
    public static class Permission {

        /**
         * 超级管理员所拥有的权限
         */
        public static String SUPER_ADMIN_PERMISSION = "**:**:**";

        /**
         * 超级管理员标签
         */
        public static String SUPER_ADMIN_LABEL = "super_admin";

        /**
         * 管理员标签
         */
        public static String ADMIN_LABEL = "admin";

        /**
         * 默认权限字符串
         */
        public static String DEFAULT_PERMISSION = "#";

        /**
         * 关键信息替换
         */
        public static final String KEYWORD_INFO_INSTEAD_STR = "***********************";

        /**
         * 允许匿名访问的静态资源列表
         */
        public static final String[] STATIC_WITHE_LIST = new String[]{
                "/",
                "/js/**",
                "/css/**",
                "/img/**",
                "/fonts/**",
                "/index.html",
                "/favicon.ico",
                "/doc.html",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v3/**"
        };
    }


    /**
     * 日志列表
     */
    public static class LogType {

        /**
         * 新增数据
         */
        public static final String SAVE_OPERATE = "新增数据";

        /**
         * 更新数据
         */
        public static final String UPDATE_OPERATE = "更新数据";

        /**
         * 删除数据
         */
        public static final String DELETE_OPERATE = "删除数据";

        /**
         * 批量删除
         */
        public static final String BATCH_DELETE_OPERATE = "批量删除";

        /**
         * 导入数据
         */
        public static final String IMPORT_OPERATE = "导入数据";

        /**
         * 导出数据
         */
        public static final String EXPORT_OPERATE = "导出数据";

        /**
         * 授权认证
         */
        public static final String AUTHORIZATION_OPERATE = "授权认证";

        /**
         * 上传文件
         */
        public static final String UPLOAD_OPERATE = "上传文件";

        /**
         * 审核
         */
        public static final String VERIFY_OPERATE = "审核";

        /**
         * 发邮邮箱
         */
        public static final String SEND_EMAIL_OPERATE = "发邮邮箱";


    }

}
