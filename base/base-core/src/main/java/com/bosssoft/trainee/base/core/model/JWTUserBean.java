package com.bosssoft.trainee.base.core.model;

import lombok.Data;

import java.util.List;

@Data
public class JWTUserBean {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 是否超级管理员
     */
    private Integer isSuperAdmin;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 用户角色
     */
    private List<String> roles;

    /**
     * 用户部门
     */
    private List<String> depts;
}