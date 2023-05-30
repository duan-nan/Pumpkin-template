package com.pumpkin.disease.config;

import com.pumpkin.disease.config.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Pumpkin
 * @description: 自定义配制类配制信息
 * @since : 2022/12/14
 */
@EnableConfigurationProperties({
        JwtProperties.class
})
@Configuration
public class PropertiesConfiguration {
}
