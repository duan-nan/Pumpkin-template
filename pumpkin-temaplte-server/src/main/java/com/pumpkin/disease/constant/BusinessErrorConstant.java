package com.pumpkin.disease.constant;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/17
 * @description: 业务逻辑错误信息常量
 * @version： 1.0
 */
public class BusinessErrorConstant {

    /**
     * 未知错误
     */
    public static class SystemError {

        /**
         * 未知错误
         */
        public static final String UNKNOWN_EXCEPTION = "未知错误！";

        /**
         * 未知地址
         */
        public static final String UNKNOWN_IP_ADDRESS = "未知地址！";

    }

    /**
     * 常见错误信息
     */
    public static class SimpleError {

        /**
         * 查询数据失败
         */
        public static final String QUERY_DATA_FAIL = "查询数据失败！";

        /**
         * 新增数据失败
         */
        public static final String SAVE_DATA_FAIL = "新增数据失败！";

        /**
         * 更新数据失败
         */
        public static final String UPDATE_DATA_FAIL = "更新数据失败！";

        /**
         * 删除数据失败
         */
        public static final String DELETE_DATA_FAIL = "删除数据失败！";

        /**
         * 请先删除子元素
         */
        public static final String PLEASE_DELETE_CHILDREN_DATA = "请先删除子元素！";

        /**
         * 请先删除角色关联用户
         */
        public static final String PLEASE_DELETE_USER_FIRST = "请先删除角色关联用户！";

        /**
         * 数据已经存在
         */
        public static final String DATA_ALREADY_EXISTED = "数据已经存在！";

        /**
         * 查询记录不存在
         */
        public static final String QUERY_RECORD_NOT_EXISTED = "查询记录不存在！";

        /**
         * 缺少必要参数
         */
        public static final String MISSING_NECESSARY_PARAMETER = "缺少必要参数！";

        /**
         * 参数接收出现错误
         */
        public static final String PARAMETER_RECEIVE_ERROR = "参数接收出现错误！";

        /**
         * 数据转换错误
         */
        public static final String DATA_TRANSFORMATION_ERROR = "数据转换错误！";

        /**
         * 查询当前登录用户信息失败
         */
        public static final String QUERY_LOGIN_USER_INFO_ERROR = "查询当前登录用户信息失败！";

        /**
         * 旧密码错误
         */
        public static final String USER_OLD_PASSWORD_ERROR = "旧密码输入错误！";
    }

    /**
     * 枚举信息错误
     */
    public static class EnumError {

        /**
         * 未找到对名称对应的枚举值
         */
        public static final String NO_ENUM_FOUNDED_BY_KEY = "未找到对名称对应的枚举值：%s";

    }

    /**
     * 文件类型错误
     */
    public static class FileError {

        /**
         * 文件上传失败
         */
        public static final String FILE_UPLOAD_ERROR = "文件上传失败！";

        /**
         * 文件删除失败
         */
        public static final String FILE_DELETE_ERROR = "文件删除失败！";

        /**
         * 文件删除失败
         */
        public static final String FILE_UPDATE_ERROR = "文件删除失败！";

        /**
         * 文件过大，无法上传
         */
        public static final String FILE_SIZE_TOO_MAX = "文件过大，无法上传！";

        /**
         * 文件夹创建失败
         */
        public static final String FLODER_CREATE_FAIL = "文件夹创建失败！";

        /**
         * 文件转换失败
         */
        public static final String FILE_TRANSLATION_FAIL = "文件转换失败！";

        /**
         * 文件不能为空
         */
        public static final String FILE_IS_NOT_EMPTY = "文件不能为空！";

        /**
         * 文件查询异常
         */
        public static final String FILE_NOT_FOUND = "文件查询异常！";

        /**
         * 文件信息不存在
         */
        public static final String FILE_NOT_EXISTED = "文件信息不存在！";
    }

    /**
     * 邮箱验证码错误
     */
    public static class EmailCaptchaError {

        /**
         * 邮箱验证码发送失败
         */
        public static final String EMAIL_SEND_FAIL = "邮件发送失败！";

        /**
         * 邮箱验证码不正确
         */
        public static final String EMAIL_CAPTCHA_ERROR = "邮箱验证码不正确！";

        /**
         * 该邮箱已经被注册
         */
        public static final String CURRENT_EMAIL_ALREADY_REGISTERED = "该邮箱已经被注册！";

    }

    /**
     * 对象存储错误
     */
    public static class ObjectStoreError {

        /**
         * 对象存储服务连接失败，请检查配置后重新测试！
         */
        public static final String OBJECT_STORE_CONNECT_ERROR = "对象存储服务连接失败，请检查配置后重新测试！";

        /**
         * 存储桶不存在，请检查配置后重新测试
         */
        public static final String NO_SUCH_BUCKET_ERROR = "存储桶不存在，请检查配置后重新测试！";


    }
}
