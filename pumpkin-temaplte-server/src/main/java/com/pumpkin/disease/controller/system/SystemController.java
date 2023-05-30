package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.annotation.AnonymousAccess;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.request.security.email.EmailRequest;
import com.pumpkin.disease.strategy.context.EmailStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Pumpkin
 * @description: 系统控制器
 * @since : 2023/4/23 14:12
 */
@RestController
@Api(tags = {"系统模块"})
@RequiredArgsConstructor
@RequestMapping("/system")
public class SystemController {

    private final EmailStrategyContext emailStrategyContext;

    /**
     * 发送邮箱验证码
     *
     * @param emailRequest 邮箱请求入参
     * @return 是否发送成功！
     */
    @ApiOperation(value = "[通用]发送邮箱验证码")
    @AnonymousAccess
    @PostMapping("/send/email/captcha")
    public ResponseResult<?> sendEmailCaptcha(@RequestBody EmailRequest emailRequest) {
        emailStrategyContext.executeSendEmailStrategy(emailRequest);
        return ResponseResult.success("发送邮箱验证码成功，15分钟内有效！");
    }

}
