package com.pumpkin.disease.config;

import com.pumpkin.disease.annotation.AnonymousAccess;
import com.pumpkin.disease.filter.JwtAuthenticationFilter;
import com.pumpkin.disease.handler.*;
import com.pumpkin.disease.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.pumpkin.disease.constant.SystemConstant.Permission.STATIC_WITHE_LIST;
import static com.pumpkin.disease.constant.SystemConstant.SecurityInterface.LOGIN_INTERFACE;
import static com.pumpkin.disease.constant.SystemConstant.SecurityInterface.LOGOUT_INTERFACE;

/**
 * @author: Pumpkin
 * @description: SpringSecurity配置类
 * @since : 2022/12/2
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    private final AuthenticationFailureHandlerImpl authenticationFailureHandler;
    private final LogoutSuccessHandlerImpl logoutSuccessHandler;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final ApplicationContext applicationContext;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(STATIC_WITHE_LIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //region 跨域配置开启 和 csrf关闭
        http.cors()
                .and()
                .csrf().disable();
        //endregion

        //region 自定义 登录/注销 处理器
        http.formLogin()
                .loginProcessingUrl(LOGIN_INTERFACE)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl(LOGOUT_INTERFACE)
                .logoutSuccessHandler(logoutSuccessHandler);
        //endregion

        //region 请求拦截
        http.authorizeRequests()
                .antMatchers(listAnonymous().toArray(new String[0])).permitAll()
                .anyRequest().authenticated();
        //endregion

        //region 添加Jwt过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //endregion

        //region 未登录/未授权 处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        //endregion
    }


    /**
     * 查找可以匿名访问的接口
     *
     * @return 匿名访问接口集合
     */
    private Set<String> listAnonymous() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> anonymousUrls = new HashSet<>();
        anonymousUrls.add("/store/**");
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (anonymousAccess != null) {
                assert infoEntry.getKey().getPatternsCondition() != null;
                anonymousUrls.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
        }
        log.info("可以匿名访问的url：{}", anonymousUrls);
        return anonymousUrls;
    }

}
