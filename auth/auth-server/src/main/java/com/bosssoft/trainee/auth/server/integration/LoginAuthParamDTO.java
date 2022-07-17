package com.bosssoft.trainee.auth.server.integration;

import lombok.Data;

import java.util.Map;

@Data
public class LoginAuthParamDTO {
    private String LoginType;
    /**
     * 请求账号
     */
    private String username;
    /**
     * 前端请求登陆参数
     */
    private Map<String, String[]> authParameters;

    /**
     * 获取前端请求参数
     *
     * @param paramter 请求的name
     * @return 请求的vlaue
     */
    public String getAuthParameter(String paramter) {
        String[] values = this.authParameters.get(paramter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }
}