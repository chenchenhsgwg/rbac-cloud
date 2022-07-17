package com.bosssoft.trainee.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.admin.api.dto.MenuButtonCodeDTO;
import com.bosssoft.trainee.admin.api.model.SysMenuButton;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuButtonMapper extends BaseMapper<SysMenuButton> {

    /**
     * 通过主键id获取菜单按钮关系编码
     *
     * @param menuButtonIds 主键ids
     * @return
     */
    List<MenuButtonCodeDTO> selectMenuCodeAndButCodeByButIds(@Param("menuButtonIds") List<Integer> menuButtonIds);
}