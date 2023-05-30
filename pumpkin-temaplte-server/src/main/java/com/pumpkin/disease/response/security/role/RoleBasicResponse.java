package com.pumpkin.disease.response.security.role;

import lombok.Data;

/**
 * @author: Pumpkin
 * @description: 角色基础信息Response
 * @since : 2023/3/1421:56
 */
@Data
public class RoleBasicResponse {

    /**
     * 角色主键id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

}
