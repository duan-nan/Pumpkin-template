package com.pumpkin.disease.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/30
 * @description: MyBatisPlus配置文件
 * @version： 1.0
 */
@Configuration
@MapperScan(basePackages = "com.pumpkin.disease.mapper.**")
public class MyBatisPlusConfiguration {

    /**
     * MyBatisPlus 分页插件
     * @return PaginationInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
