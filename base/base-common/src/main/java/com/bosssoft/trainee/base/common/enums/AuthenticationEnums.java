package com.bosssoft.trainee.base.common.enums;

import lombok.Getter;

@Getter
public enum AuthenticationEnums {
    /**
     * 用户授权过期或者其他端已登出，请重新登陆！
     */
    AUTH_FAIL(401000, "访问权限异常，请联系管理员！"),
    EXPIRED_TOKEN(401001, "用户授权过期或者其他端已登出，请重新登陆！"),
    UNKNOWN_ACCOUNT(401002, "用户账号或密码错误！"),
    DISABLED_ACCOUNT(401003, "用户账号已禁用，请联系管理员！");

    /**
     * 编码
     */
    private int code;
    /**
     * 提示信息
     */
    private String value;

    AuthenticationEnums(int code, String value) {
        this.code = code;
        this.value = value;
    }
}