package com.pumpkin.disease.aspect;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson2.JSON;
import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.constant.BusinessErrorConstant;
import com.pumpkin.disease.entity.OperateLog;
import com.pumpkin.disease.entity.User;
import com.pumpkin.disease.enums.log.LogTypeEnum;
import com.pumpkin.disease.service.OperateLogService;
import com.pumpkin.disease.utils.ip.IpUtil;
import com.pumpkin.disease.utils.user.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: Pumpkin
 * @description: 操作日志切面类
 * @since : 2023/2/14
 */
@Aspect
@Component
@RequiredArgsConstructor
public class OperateLogAspect {

    private final OperateLogService operateLogService;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.pumpkin.disease.annotation.NormalOperateLog)")
    public void doOperateLog() {
        System.out.println("进如  切入点  ");
    }

    /**
     * 切入点返回数据之后
     *
     * @param joinPoint 切入点
     * @param response  返回数据
     */
    @AfterReturning(value = "doOperateLog()", returning = "response")
    public void afterOperate(JoinPoint joinPoint, Object response) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作
        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        NormalOperateLog log = method.getAnnotation(NormalOperateLog.class);

        //region 数据获取
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        className += "." + methodName;
        User userInfo = UserUtil.getUserInfo();
        String ipAddress = IpUtil.getIpAddress(request);
        String ipSource = IpUtil.getIpSource(ipAddress);
        //endregion

        //region 数据转换
        assert request != null;
        OperateLog normalOperateLog = OperateLog
                .builder()
                .operateModule(api.tags()[0])
                .operateUrl(request.getRequestURI())
                .operateType(LogTypeEnum.getEnumByName(log.operateType()).getTypeCode())
                .operateMethod(className)
                .requestType(Objects.requireNonNull(request).getMethod())
                .requestParams(LogTypeEnum.UPLOAD_OPERATE.getTypeName().equals(log.operateType()) ? "{}" : JSON.toJSONString(joinPoint.getArgs()[0], String.valueOf(SerializerFeature.IgnoreErrorGetter)))
                .responseData(JSON.toJSONString(response, String.valueOf(SerializerFeature.IgnoreErrorGetter)))
                .operateDescription(apiOperation.value())
                .userId(userInfo.getId())
                .username(userInfo.getUsername())
                .operateIpAddress(ipAddress)
                .operateIpSource(ipSource)
                .build();
        //endregion

        //region 新增操作日志
        if (!operateLogService.save(normalOperateLog)) {
            throw new BaseException(BusinessErrorConstant.SimpleError.SAVE_DATA_FAIL);
        }
        //endregion

    }

}
