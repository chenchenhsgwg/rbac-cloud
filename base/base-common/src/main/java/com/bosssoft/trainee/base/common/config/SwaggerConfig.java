package com.bosssoft.trainee.base.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableKnife4j
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    /**
     * 扫描api包路径
     */
    @Value("${swagger.config.basePackage}")
    private String basePackage;

    /**
     * title
     */
    @Value("${swagger.config.title:api管理}")
    private String title;

    /**
     * 服务地址配置
     */
    @Value("${swagger.config.serviceUrl:localhost}")
    private String serviceUrl;

    /**
     * 版本
     */
    @Value("${swagger.config.version:1.0}")
    private String version;

    /**
     * 说明
     */
    @Value("${swagger.config.description:description}")
    private String description;

    /**
     * 联系人：姓名
     */
    @Value("${swagger.config.contact.name:cs}")
    private String contactName;

    /**
     * 配置分组摘要
     */
    @Bean(value = "userApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置基本信息
     */
    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(serviceUrl)
                .version(version)
                .contact(new Contact(contactName, null, null))
                .build();
    }
}