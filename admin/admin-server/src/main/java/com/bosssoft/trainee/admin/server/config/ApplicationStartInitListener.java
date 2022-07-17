package com.bosssoft.trainee.admin.server.config;

import com.bosssoft.trainee.admin.server.biz.SysRoleBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ApplicationStartInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private SysRoleBiz roleBiz;

    /**
     * 初始启动quartz
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            log.info("初始缓存数据开始");
            roleBiz.refreshRoleCache();
            log.info("初始缓存数据结束");
        } catch (Exception e) {
            log.info("初始缓存数据异常: {}", e.getMessage());
        }
    }

}
