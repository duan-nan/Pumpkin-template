package com.pumpkin.disease.config.properties;


import com.pumpkin.disease.constant.PropertiesConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Pumpkin
 * @description: Jwt配制文件类
 * @since : 2022/12/14
 */
@Getter
@Setter
@ConfigurationProperties(PropertiesConstant.JWT)
public class JwtProperties {

    /**
     * Jwt配置类
     */
    private ConfigEntity jwt;

    @Getter
    @Setter
    public static class ConfigEntity {

        /**
         * Jwt秘钥
         */
        private String secret;

        /**
         * Jwt超时时间
         */
        private Integer expireTime;

        /**
         * 请求头
         */
        private String requestHeader;
    }

}
