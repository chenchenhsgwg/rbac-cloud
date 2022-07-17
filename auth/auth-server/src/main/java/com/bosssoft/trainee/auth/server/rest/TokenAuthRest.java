package com.bosssoft.trainee.auth.server.rest;

import com.bosssoft.trainee.base.common.constant.RedisKeysConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenAuthRest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * token缓存认证，匹配key是否存在
     *
     * @param token 用户登陆token
     * @return ture存在，false不存在
     */
    @GetMapping("/verify")
    public Boolean verifyToken(@RequestParam("token") String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        return stringRedisTemplate.hasKey(RedisKeysConstant.USER_TOKENS_ACCESS + token);
    }
}
