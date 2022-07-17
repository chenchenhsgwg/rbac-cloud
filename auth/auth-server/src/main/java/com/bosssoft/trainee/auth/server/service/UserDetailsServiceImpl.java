package com.bosssoft.trainee.auth.server.service;

import com.bosssoft.trainee.admin.api.dto.UserInfoDTO;
import com.bosssoft.trainee.admin.api.model.BaseUser;
import com.bosssoft.trainee.auth.server.integration.LoginAuthParamDTO;
import com.bosssoft.trainee.auth.server.integration.LoginAuthenticationContext;
import com.bosssoft.trainee.auth.server.integration.handler.LoginAuthHandler;
import com.bosssoft.trainee.auth.server.bean.JwtTokenUser;
import com.bosssoft.trainee.auth.server.enums.OAuthRoleEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired(required = false)
    private List<LoginAuthHandler> loginAuthHandlers;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginAuthParamDTO loginAuthParam = LoginAuthenticationContext.get();

        if (loginAuthParam == null) {
            loginAuthParam = new LoginAuthParamDTO();
        }
        loginAuthParam.setUsername(username);

        UserInfoDTO userInfoDTO = this.authenticate(loginAuthParam);
        if (userInfoDTO == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        BaseUser baseUser = userInfoDTO.getBaseUser();
        if (baseUser == null) {
            throw new UsernameNotFoundException("未查询到用户名");
        }


        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(OAuthRoleEnums.USER.getRole());
        JwtTokenUser jwtUser = new JwtTokenUser(username, baseUser.getPassword(), authorities);
        jwtUser.setName(baseUser.getUsername());
        jwtUser.setUserId(baseUser.getId());
        jwtUser.setIsSuperAdmin(baseUser.getIsSuperAdmin());
        jwtUser.setTenantId(baseUser.getTenantId());
        jwtUser.setDepts(new ArrayList<>());
        jwtUser.setRoles(userInfoDTO.getRoles());

        return jwtUser;
    }

    /**
     * 通过前端请求参数解析，并获取用户信息
     *
     * @param loginAuthParam 前端请求参数
     * @return 用户信息
     */
    private UserInfoDTO authenticate(LoginAuthParamDTO loginAuthParam) {
        if (this.loginAuthHandlers != null) {
            for (LoginAuthHandler loginAuthHandler : loginAuthHandlers) {
                // 判断当前登陆认证类型是否匹配
                if (loginAuthHandler.support(loginAuthParam)) {
                    // 通过各自登陆方式获取用户信息
                    return loginAuthHandler.authenticate(loginAuthParam);
                }
            }
        }
        return null;
    }
}
