package com.bosssoft.trainee.auth.server.enums;

import lombok.Getter;

@Getter
public enum OAuthRoleEnums {
    /**
     * OAuth认证角色：管理员
     */
    ADMIN("ROLE_ADMIN"),
    /**
     * OAuth认证角色：普通用户
     */
    USER("ROLE_USER"),
    /**
     * OAuth认证角色：游客
     */
    ANONYMOUS("ROLE_ANONYMOUS");

    /**
     * 角色编码
     */
    private String role;

    OAuthRoleEnums(String role) {
        this.role = role;
    }
}
