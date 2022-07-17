package com.bosssoft.trainee.auth.server;

import com.bosssoft.trainee.base.common.annotation.EnableSwaggerConfig;
import com.bosssoft.trainee.base.common.annotation.JacksonDateTimeSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableSwaggerConfig
// 开启认证服务器
@EnableAuthorizationServer
@EnableDiscoveryClient
// 配置日期格式化
@JacksonDateTimeSerializer
// 启用Feign
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.bosssoft.trainee.admin.api", "com.bosssoft.trainee.auth.server"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}