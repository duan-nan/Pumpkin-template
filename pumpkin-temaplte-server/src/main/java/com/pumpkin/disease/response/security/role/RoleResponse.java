package com.pumpkin.disease.response.security.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Pumpkin
 * @description: 角色响应数据
 * @since : 2022/12/8
 */
@Data
public class RoleResponse {

    /**
     * 角色主键id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String roleLabel;

    /**
     * 角色秘钥
     */
    private String roleSecret;

    /**
     * 角色秘钥
     */
    private Integer orderNum;

    /**
     * 是否禁用
     */
    private Boolean isDisabled;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    /**
     * 用户数量
     */
    private Integer userCount;
}
