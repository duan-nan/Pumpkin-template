package com.pumpkin.disease.annotation;


import java.lang.annotation.*;

/**
 * 操作日志注解
 * @author Pumpkin
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NormalOperateLog {

    /**
     * 操作方式
     * @return 操作类型
     */
    String operateType() default "";

}
