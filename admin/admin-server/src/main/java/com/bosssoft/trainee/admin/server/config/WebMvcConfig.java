package com.bosssoft.trainee.admin.server.config;

import com.bosssoft.trainee.auth.client.interceptor.AuthTokenRestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 自定义配置mvc拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthTokenRestInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");
    }
}