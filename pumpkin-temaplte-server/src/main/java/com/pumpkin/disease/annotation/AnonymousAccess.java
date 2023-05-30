package com.pumpkin.disease.annotation;

import java.lang.annotation.*;

/**
 * @author: Pumpkin
 * @description: 匿名访问注解
 * @since : 2022/12/2
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
