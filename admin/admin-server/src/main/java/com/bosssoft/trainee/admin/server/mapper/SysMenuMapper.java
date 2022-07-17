package com.bosssoft.trainee.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.admin.api.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过菜单ids，获取菜单列表
     *
     * @param menuIds 菜单ids
     * @return
     */
    List<SysMenu> selectMenuListByMenuIds(@Param("menuIds") List<String> menuIds);
}