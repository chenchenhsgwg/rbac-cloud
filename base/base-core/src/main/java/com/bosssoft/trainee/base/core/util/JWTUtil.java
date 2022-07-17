package com.bosssoft.trainee.base.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bosssoft.trainee.base.core.constant.JwtUserConstant;
import com.bosssoft.trainee.base.core.model.JWTUserBean;

import java.util.List;

public class JWTUtil {
    /**
     * 获得token中的信息
     *
     * @return token中包含的UserBean信息
     */
    public static JWTUserBean getUserBeanByToken(String token) {
        JWTUserBean userBean = new JWTUserBean();
        try {
            DecodedJWT jwt = JWT.decode(token);
            userBean.setUsername(jwt.getClaim(JwtUserConstant.JWT_KEY_USER_NAME).asString());
            userBean.setName(jwt.getClaim(JwtUserConstant.JWT_KEY_NAME).asString());
            userBean.setUserId(jwt.getClaim(JwtUserConstant.JWT_KEY_USER_ID).asString());
            userBean.setTenantId(jwt.getClaim(JwtUserConstant.JWT_KEY_TENANT_ID).asString());

            List<String> roles = jwt.getClaim(JwtUserConstant.JWT_KEY_USER_ROLES).asList(String.class);
            userBean.setRoles(roles);

            List<String> depts = jwt.getClaim(JwtUserConstant.JWT_KEY_DEPARTS).asList(String.class);
            userBean.setDepts(depts);

            userBean.setIsSuperAdmin(jwt.getClaim(JwtUserConstant.JWT_KEY_USER_IS_ADMIN).asInt());
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return userBean;
    }

    /**
     * 获得token中的信息
     *
     * @param token 认证信息
     * @param key   token中属性key
     * @return token中数据value
     */
    public static String getJwtValue(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息集
     *
     * @param token 认证信息
     * @param key   token中属性key
     * @return token中数据values
     */
    public static List<String> getJwtValues(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asList(String.class);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}