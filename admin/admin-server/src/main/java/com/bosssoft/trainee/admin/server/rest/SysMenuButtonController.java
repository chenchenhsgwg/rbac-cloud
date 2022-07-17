package com.bosssoft.trainee.admin.server.rest;

import com.bosssoft.trainee.admin.api.model.SysMenuButton;
import com.bosssoft.trainee.admin.server.biz.SysMenuButtonBiz;
import com.bosssoft.trainee.auth.client.annotation.CheckAuthToken;
import com.bosssoft.trainee.base.core.rest.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CheckAuthToken
@RestController
@RequestMapping("/sysMenuButtons")
@Api(tags = "系统基础菜单按钮表")
public class SysMenuButtonController extends BaseController<SysMenuButtonBiz, SysMenuButton> {

}