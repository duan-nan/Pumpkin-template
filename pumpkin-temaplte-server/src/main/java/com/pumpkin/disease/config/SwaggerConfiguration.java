package com.pumpkin.disease.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: Pumpkin
 * @createDate: 2022/11/30
 * @description: Swagger配制信息
 * @version： 1.0
 */
@Configuration
public class SwaggerConfiguration {

    @Value("${application.swagger.isEnable}")
    private boolean isEnable;

    @Bean
    public Docket createSecurityRestApi() {
        return createRestApi("权限分组", "com.muzinan.disease.controller.security");
    }

    @Bean
    public Docket createSystemRestApi() {
        return createRestApi("系统分组", "com.muzinan.disease.controller.system");
    }

    /**
     * 创建api
     *
     * @param groupName   分组名称
     * @param basePackage 包路径
     * @return Docket
     */
    public Docket createRestApi(String groupName, String basePackage) {
        return new Docket(DocumentationType.OAS_30)
                .enable(isEnable)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api简介信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Pumpkin后台管理系统服务接口")
                .description("Pumpkin后台管理系统服务接口文档...")
                .contact(new Contact("Pumpkin", "www.nanjustar.top", "486356410@qq.com"))
                .version("v1.0")
                .build();
    }

}
