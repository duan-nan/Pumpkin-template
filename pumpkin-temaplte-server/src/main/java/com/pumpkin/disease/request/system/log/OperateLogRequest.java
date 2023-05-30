package com.pumpkin.disease.request.system.log;

import com.pumpkin.disease.base.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: MuZiNan
 * @description: 操作日志请求体
 * @since : 2023/2/15
 */
@Getter
@Setter
public class OperateLogRequest extends BaseRequest {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username")
    private String username;

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型", name = "operateType")
    private Integer operateType;

    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式", name = "requestType")
    private String requestType;

}
