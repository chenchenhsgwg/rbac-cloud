package com.bosssoft.trainee.admin.server.rest;

import com.bosssoft.trainee.admin.api.model.SysRole;
import com.bosssoft.trainee.admin.api.vo.SysRoleTreeVO;
import com.bosssoft.trainee.admin.server.biz.SysRoleBiz;
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
@RequestMapping("/sysRoles")
@Api(tags = "系统基础角色表")
public class SysRoleController extends BaseController<SysRoleBiz, SysRole> {

    @GetMapping("/tree")
    @ApiOperation(value = "获取角色树")
    public ObjectResult<List<SysRoleTreeVO>> getSysRoleTree() {
        ObjectResult<List<SysRoleTreeVO>> result = new ObjectResult<>();
        result.setData(this.baseBiz.getSysRoleTree());
        return result;
    }

    @GetMapping("/refresh/role/auth")
    @ApiOperation(value = "刷新角色权限缓存")
    public ObjectResult<Void> refreshRoleCache() {
        ObjectResult<Void> result = new ObjectResult<>();
        this.baseBiz.refreshRoleCache();
        return result;
    }
}