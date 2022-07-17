package com.bosssoft.trainee.auth.server.config;

import com.bosssoft.trainee.auth.server.bean.JwtTokenUser;
import com.bosssoft.trainee.base.core.constant.JwtUserConstant;
import com.bosssoft.trainee.base.common.util.DateTimeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuthJWTTokenStore {

    /**
     * TokenEnhancer的子类，帮助程序在JWT编码的令牌值和OAuth身份验证信息之间进行转换，同时充当TokenEnhancer授予令牌的时间。
     * 把自己设置的jwt签名加入accessTokenConverter中
     */
    @Bean("accessTokenConverter")
    public JwtAccessTokenConverter JwtAccessTokenConverter() {

        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            /***
             * 重写增强token方法,用于自定义一些token返回的信息
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                // 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
                JwtTokenUser jwtTokenUser = (JwtTokenUser) authentication.getUserAuthentication().getPrincipal();
                final Map<String, Object> additionalInformation = new HashMap<>();

                // 获取过期秒
                LocalDateTime expireTime = LocalDateTime.now().plusSeconds(accessToken.getExpiresIn());
                // 设置过期日期
                additionalInformation.put(JwtUserConstant.JWT_KEY_EXPIRE, DateTimeUtils.format(expireTime));

                additionalInformation.put(JwtUserConstant.JWT_KEY_USER_NAME, jwtTokenUser.getUsername());
                additionalInformation.put(JwtUserConstant.JWT_KEY_NAME, jwtTokenUser.getName());
                additionalInformation.put(JwtUserConstant.JWT_KEY_USER_ID, jwtTokenUser.getUserId());
                additionalInformation.put(JwtUserConstant.JWT_KEY_USER_ROLES, jwtTokenUser.getRoles());
                additionalInformation.put(JwtUserConstant.JWT_KEY_DEPARTS, jwtTokenUser.getDepts());
                additionalInformation.put(JwtUserConstant.JWT_KEY_TENANT_ID, jwtTokenUser.getTenantId());

                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }

        };
        return accessTokenConverter;
    }
}
