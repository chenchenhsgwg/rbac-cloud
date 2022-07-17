package com.bosssoft.trainee.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cs-auth")
public interface AuthFeign {
    /**
     * token缓存认证，匹配key是否存在
     *
     * @param token 用户登陆token
     * @return ture存在，false不存在
     */
    @GetMapping("/token/verify")
    Boolean verifyToken(@RequestParam("token") String token);
}
