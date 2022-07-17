package com.bosssoft.trainee.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.bosssoft.trainee.admin.api.model.SysMenuButton;
import com.bosssoft.trainee.base.common.constant.HttpHeadersConstant;
import com.bosssoft.trainee.base.common.constant.HttpStatusConstant;
import com.bosssoft.trainee.base.common.constant.RedisKeysConstant;
import com.bosssoft.trainee.base.core.model.JWTUserBean;
import com.bosssoft.trainee.base.core.result.Result;
import com.bosssoft.trainee.base.common.util.BeanUtil;
import com.bosssoft.trainee.base.core.util.JWTUtil;
import com.bosssoft.trainee.gateway.feign.AuthFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class RoleAuthFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AuthFeign authFeign;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 请求url地址
        RequestPath requestPath = request.getPath();
        String requestURL = requestPath.toString();

        boolean isExcludeURL = this.isExcludeURL(requestURL);
        if (isExcludeURL) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(HttpHeadersConstant.HEADERS_USER_AUTH_TOKEN);
        if (StringUtils.isBlank(token)) {
            return this.getVoidMono(exchange, new Result(HttpStatusConstant.AUTHORITY_FAIL, "没有登陆请重新登陆"));
        }

        // 验证token
        token = token.replace("Bearer ", "");

        // 查询redis中是否存在token
        Boolean hasToken = authFeign.verifyToken(token);
        if (!hasToken) {
            return this.getVoidMono(exchange, new Result(HttpStatusConstant.AUTHORITY_FAIL, "认证错误"));
        }

        // 验证角色菜单权限
        // 请求方式
        HttpMethod method = request.getMethod();

        JWTUserBean userBean = JWTUtil.getUserBeanByToken(token);
        List<String> roles = userBean.getRoles();
        // 菜单权限判断
        if (BeanUtil.isNotEmpty(roles)) {
            // 超级管理员跳过url地址验证
            if (RedisKeysConstant.ADMIN_ROLES_AUTH_KEY.equals(roles.get(0))) {
                return chain.filter(exchange);
            }
            // TODO 暂时支持单角色权限
            List<SysMenuButton> buttons = (List<SysMenuButton>) redisTemplate.opsForHash()
                    .get(RedisKeysConstant.ROLES_AUTH_KEY, roles.get(0));
            boolean isMatch = isPathMatch(buttons, method, requestURL);
            if (!isMatch) {
                return this.getVoidMono(exchange, new Result(HttpStatusConstant.AUTHORITY_FAIL, "没有权限登陆"));
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 是否放行RUL地址
     * （1）? 匹配一个字符（除过操作系统默认的文件分隔符）
     * （2）* 匹配0个或多个字符
     * （3）**匹配0个或多个目录
     *
     * @param requestURL 请求url地址
     * @return true：允许放行,false：访问限制
     */
    public boolean isExcludeURL(String requestURL) {
        //todo 可以配置放行地址
        List<String> urls = new ArrayList<>();
        urls.add("/api/v2/**");
        urls.add("/api/*/v2/api-docs");
        urls.add("/api/auth/oauth/token");

        // 默认不放行
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 匹配放行地址
        for (String url : urls) {
            if (antPathMatcher.match(url, requestURL)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 网关统一抛异常
     */
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, Result body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * 是否有权限获取访问当前请求
     *
     * @param buttons    权限列表
     * @param method     请求方式
     * @param requestURL 请求地址
     * @return false：权限不足，true允许登陆
     */
    private boolean isPathMatch(List<SysMenuButton> buttons, HttpMethod method, String requestURL) {
        // 默认不放行
        // 判断当前地址是否匹配权限
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (SysMenuButton button : buttons) {
            // 判断请求方式是否一致
            if (method.matches(button.getMethod())) {
                // 判断请求地址是否一致
                if (antPathMatcher.match(button.getUrl(), requestURL)) {
                    return true;
                }
            }
        }
        return false;
    }
}
