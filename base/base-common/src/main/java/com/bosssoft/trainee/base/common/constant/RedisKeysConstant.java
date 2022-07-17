package com.bosssoft.trainee.base.common.constant;

public class RedisKeysConstant {
    /**
     * 用户token集
     */
    public static final String USER_TOKENS = "user:tokens:";

    /**
     * 用户token前缀
     */
    public static final String USER_TOKENS_ACCESS = "user:tokens:access:";

    /**
     * 角色按钮权限。
     * 网关url匹配使用
     */
    public static final String ROLES_BUTTON_AUTH_KEY = "roles:button:auth";

    /**
     * 角色菜单按钮权限。
     * 用户获取动态菜单使用
     */
    public static final String ROLES_AUTH_KEY = "role:auth";


    /**
     * 超级管理员角色。
     */
    public static final String ADMIN_ROLES_AUTH_KEY = "admin";
}