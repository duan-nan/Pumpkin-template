package com.pumpkin.disease.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Pumpkin
 * @description: Web配制
 * @since : 2022/12/5
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("PUT", "POST", "GET", "HEAD", "DELETE", "OPTIONS")
                .allowedOriginPatterns("*")
                .maxAge(3600)
                .allowCredentials(true);
    }

}
