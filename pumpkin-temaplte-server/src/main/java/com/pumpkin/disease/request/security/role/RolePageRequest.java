package com.pumpkin.disease.request.security.role;

import com.pumpkin.disease.base.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Pumpkin
 * @description: 角色分页请求入参
 * @since : 2022/12/8
 */
@Getter
@Setter
public class RolePageRequest extends BaseRequest {

    /**
     * 角色标识
     */
    @ApiModelProperty(value = "角色标识", name = "roleLabel")
    private String roleLabel;

    /**
     * 是否禁用
     */
    @ApiModelProperty(value = "是否禁用", name = "isDisabled")
    private Boolean isDisabled;

}
