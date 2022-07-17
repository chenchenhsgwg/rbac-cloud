package com.bosssoft.trainee.auth.server.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtTokenUser extends User implements UserDetails {
    /**
     * 用户id
     */
    private String userId;

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

    public JwtTokenUser(String username, String password, Collection<GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}