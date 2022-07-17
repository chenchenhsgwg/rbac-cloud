package com.bosssoft.trainee.base.common.enums;

import lombok.Getter;

@Getter
public enum HttpStatusEnums {
    /**
     * 业务响应：成功
     */
    SUCCESS(200, "success"),
    /**
     * 业务响应：失败
     */
    FAIL(500, "fail"),
    /**
     * 业务响应：没有访问权限
     */
    AUTHORITY_FAIL(401, "authority_fail");

    /**
     * 编码
     */
    private int code;
    /**
     * 提示信息
     */
    private String value;

    HttpStatusEnums(int code, String value) {
        this.code = code;
        this.value = value;
    }
}