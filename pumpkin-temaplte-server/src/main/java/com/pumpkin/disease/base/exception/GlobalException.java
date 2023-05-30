package com.pumpkin.disease.base.exception;

import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.enums.http.HttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;
import java.util.Objects;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SystemError.UNKNOWN_IP_ADDRESS;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/24
 * @description: 统一异常信息处理
 * @version： 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 全局异常处理
     *
     * @param e 异常信息
     * @return 错误信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<?> globalException(Exception e) {
        e.printStackTrace();
        return ResponseResult.fail(HttpCodeEnum.OPERATOR_IS_FAILED.getMessage());
    }

    /**
     * 基础异常处理
     *
     * @param baseException 基础异常信息类
     * @return 错误结果
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult<?> baseException(BaseException baseException) {
        log.error("异常信息 => {}", baseException.getMessage());
        return ResponseResult.fail(baseException.getMessage());
    }

    /**
     * 参数校验异常处理
     *
     * @param methodArgumentNotValidException 异常信息
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<?> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseResult.fail(Objects.requireNonNull(methodArgumentNotValidException.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 未知主机异常
     *
     * @param unknownHostException 异常信息
     * @return 错误信息
     */
    @ExceptionHandler(UnknownHostException.class)
    public ResponseResult<?> unknownHostException(UnknownHostException unknownHostException) {
        unknownHostException.printStackTrace();
        return ResponseResult.fail(UNKNOWN_IP_ADDRESS);
    }

    /**
     * 无访问权限错误
     *
     * @param accessDeniedException 异常信息
     * @return 错误信息
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult<?> accessDeniedException(AccessDeniedException accessDeniedException) {
        return ResponseResult.fail(HttpCodeEnum.PERMISSION_NOT_DEFINED);
    }

}
