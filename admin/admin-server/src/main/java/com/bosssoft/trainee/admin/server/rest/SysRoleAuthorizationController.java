package com.bosssoft.trainee.admin.server.rest;

import com.bosssoft.trainee.admin.api.model.SysRoleAuthorization;
import com.bosssoft.trainee.admin.api.vo.RoleAuthorizationVO;
import com.bosssoft.trainee.admin.server.biz.SysRoleAuthorizationBiz;
import com.bosssoft.trainee.auth.client.annotation.CheckAuthToken;
import com.bosssoft.trainee.base.common.constant.MessageConstant;
import com.bosssoft.trainee.base.common.enums.HttpStatusEnums;
import com.bosssoft.trainee.base.core.result.ObjectResult;
import com.bosssoft.trainee.base.core.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CheckAuthToken
@RestController
@RequestMapping("/sysRoleAuthorizations")
@Api(tags = "系统角色授权关系表")
public class SysRoleAuthorizationController extends BaseController<SysRoleAuthorizationBiz, SysRoleAuthorization> {

    /**
     * 角色授权接口，角色和菜单做关联
     *
     * @param roleCode    角色编码
     * @param roleMenusVO 角色菜单
     */
    @PostMapping("/{roleCode}")
    @ApiOperation("角色授权接口，角色和菜单做关联")
    public ObjectResult setRoleAuth(
            @PathVariable("roleCode") @ApiParam("角色编码") String roleCode,
            @RequestBody @ApiParam("角色权限") RoleAuthorizationVO roleMenusVO) {
        ObjectResult result = new ObjectResult();
        if (StringUtils.isBlank(roleCode)) {
            result.setStatus(HttpStatusEnums.FAIL.getCode());
            result.setMessage(MessageConstant.MVC_VALIDATE_MSG);
            return result;
        }
        this.baseBiz.setRoleAuth(roleCode, roleMenusVO);
        return result;
    }

    /**
     * 获取角色对应的菜单权限id
     *
     * @param roleCode 角色编码
     */
    @GetMapping("/{roleCode}/resourceType/{resourceType}")
    @ApiOperation("获取角色对应的权限集合")
    public ObjectResult<List<String>> getAuthIdByRoleCode(
            @PathVariable("roleCode") @ApiParam("角色编码") String roleCode,
            @PathVariable("resourceType") @ApiParam("权限类型") Integer resourceType) {
        ObjectResult<List<String>> result = new ObjectResult<>();
        if (StringUtils.isBlank(roleCode) || null == resourceType) {
            result.setStatus(HttpStatusEnums.FAIL.getCode());
            result.setMessage("请选择角色，查看授权信息！");
            return result;
        }
        result.setData(this.baseBiz.getAuthIdByRoleCode(roleCode, resourceType));
        return result;
    }

    /**
     * 获取角色对应的菜单权限集合
     *
     * @param roleCode 角色编码
     */
    @GetMapping("/roleCode/{roleCode}/menus")
    @ApiOperation("获取角色对应的菜单权限集合")
    public ObjectResult<List<String>> getMenuAuthByRoleCode(
            @PathVariable("roleCode") @ApiParam("角色编码") String roleCode) {
        ObjectResult<List<String>> result = new ObjectResult<>();
        if (StringUtils.isBlank(roleCode)) {
            result.setStatus(HttpStatusEnums.FAIL.getCode());
            result.setMessage("请选择角色，查看授权信息！");
            return result;
        }
        result.setData(this.baseBiz.getMenuAuthByRoleCode(roleCode));
        return result;
    }
}