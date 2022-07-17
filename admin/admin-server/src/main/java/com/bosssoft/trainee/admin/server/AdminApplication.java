package com.bosssoft.trainee.admin.server;

import com.bosssoft.trainee.base.common.annotation.JacksonDateTimeSerializer;
import com.bosssoft.trainee.base.common.annotation.EnableSwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 启动注册中心
@EnableDiscoveryClient
// 启用Feign
@EnableFeignClients
// 配置日期格式化
@JacksonDateTimeSerializer
// 开启Swagger
@EnableSwaggerConfig
@MapperScan("com.bosssoft.trainee.admin.server.mapper")
@SpringBootApplication(excludeName = "exclude=DataSourceAutoConfiguration.class")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}