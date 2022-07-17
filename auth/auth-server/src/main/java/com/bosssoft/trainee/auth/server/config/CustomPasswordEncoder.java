package com.bosssoft.trainee.auth.server.config;

import com.bosssoft.trainee.auth.server.integration.LoginAuthenticationContext;
import com.bosssoft.trainee.auth.server.integration.LoginAuthParamDTO;
import com.bosssoft.trainee.base.common.util.Sha256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class CustomPasswordEncoder extends BCryptPasswordEncoder {

    /**
     * 自定义密码验证
     *
     * @param rawPassword     原始密码(base64编码)
     * @param encodedPassword 数据库密码
     * @return true：匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        LoginAuthParamDTO loginAuthParam = LoginAuthenticationContext.get();

        // 只要不是密码方式，则采用默认验证方式
        if (StringUtils.isNotEmpty(loginAuthParam.getLoginType())) {
            return super.matches(rawPassword, encodedPassword);
        }

        if (encodedPassword != null && encodedPassword.length() != 0) {
            String password = new String(Base64.getDecoder().decode(rawPassword.toString()));
            password = Sha256Util.getSHA256(password);
            if (password.equals(encodedPassword)) {
                return true;
            }
        }
        return super.matches(rawPassword, encodedPassword);
    }
}