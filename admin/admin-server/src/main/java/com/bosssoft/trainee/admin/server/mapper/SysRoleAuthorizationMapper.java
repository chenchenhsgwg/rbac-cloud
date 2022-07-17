package com.bosssoft.trainee.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.admin.api.dto.RoleMenuAuthDTO;
import com.bosssoft.trainee.admin.api.model.SysRoleAuthorization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleAuthorizationMapper extends BaseMapper<SysRoleAuthorization> {
    /**
     * 通过角色编码获取角色权限
     *
     * @param roleCodeList 角色编码集合
     * @return
     */
    List<SysRoleAuthorization> selectRoleAuthByRoleCodes(@Param("roleCodeList") List<String> roleCodeList);

    /**
     * 获取角色的菜单权限
     *
     * @param roleCode 角色编码
     * @return
     */
    List<String> selectMenuAuthByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 获取角色菜单按钮权限列表
     *
     * @return 角色菜单列表
     */
    List<RoleMenuAuthDTO> selectRoleMenus();
}