package com.bosssoft.trainee.admin.server.rest;

import com.bosssoft.trainee.admin.api.model.SysMenu;
import com.bosssoft.trainee.admin.api.vo.SysMenuTreeVO;
import com.bosssoft.trainee.admin.server.biz.SysMenuBiz;
import com.bosssoft.trainee.auth.client.annotation.CheckAuthToken;
import com.bosssoft.trainee.base.core.result.ObjectResult;
import com.bosssoft.trainee.base.core.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CheckAuthToken
@RestController
@RequestMapping("/sysMenus")
@Api(tags = "系统基础菜单表")
public class SysMenuController extends BaseController<SysMenuBiz, SysMenu> {

    @GetMapping("/tree")
    @ApiOperation(value = "获取菜单树")
    public ObjectResult<List<SysMenuTreeVO>> getSysMenuTree() {
        ObjectResult<List<SysMenuTreeVO>> result = new ObjectResult<>();
        result.setData(this.baseBiz.getSysMenuTree());
        return result;
    }
}