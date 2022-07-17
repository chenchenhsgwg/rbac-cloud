package com.bosssoft.trainee.auth.server.feign;

import com.bosssoft.trainee.admin.api.dto.UserInfoDTO;
import com.bosssoft.trainee.base.core.result.ObjectResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "admin")
public interface AdminFeign {
    /**
     * 获取用户信息：用户基本信息、角色集
     */
    @GetMapping("/baseUsers/info/{username}")
    ObjectResult<UserInfoDTO> getUserInfoByUsername(@PathVariable("username") String username);

}
