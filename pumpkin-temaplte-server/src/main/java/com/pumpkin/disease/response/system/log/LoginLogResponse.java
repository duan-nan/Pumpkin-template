package com.pumpkin.disease.response.system.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Pumpkin
 * @version 1.0
 * @description 登陆日志Response类
 * @date 2023/2/1
 */
@Data
public class LoginLogResponse {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源地址
     */
    private String ipSource;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登陆状态（0->失败 1->成功）
     */
    private Boolean loginStatus;

    /**
     * 响应数据
     */
    private String responseMessage;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
}
