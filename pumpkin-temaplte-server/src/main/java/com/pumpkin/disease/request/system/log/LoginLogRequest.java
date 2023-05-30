package com.pumpkin.disease.request.system.log;

import com.pumpkin.disease.base.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: MuZiNan
 * @description: 登陆日志请求入参
 * @since : 2023/2/1
 */
@Getter
@Setter
public class LoginLogRequest extends BaseRequest {

    /**
     * ip来源地址
     */
    @ApiModelProperty(value = "ip来源地址", name = "ipSource")
    private String ipSource;

    /**
     * 登陆状态
     */
    @ApiModelProperty(value = "登陆状态", name = "loginStatus")
    private Boolean loginStatus;


}
