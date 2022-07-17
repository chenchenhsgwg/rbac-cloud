package com.bosssoft.trainee.admin.api.dto;

import com.bosssoft.trainee.admin.api.model.BaseUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 5492050756300144926L;

    /**
     * 用户基本信息
     */
    @ApiModelProperty("用户基本信息")
    private BaseUser baseUser;

    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码集")
    private List<String> roles;

    /**
     * 角色对应的菜单树
     */
    @ApiModelProperty("角色对应的菜单树")
    private List<MenuTreeDTO> menuTree;

    /**
     * 菜单按钮对应关系
     * <p>
     * 数据格式：
     * menuButton:
     * {
     * menuCode1:{ butCode1:1, butCode2:1, ... }，
     * menuCode2:{ butCode1:1, butCode2:1, ... },
     * ...
     * }
     * 目的：
     * 方便前端做按钮权限认证
     * </p>
     */
    @ApiModelProperty("菜单按钮对应关系")
    private MenuButtonDTO<String, MenuButtonDTO<String, Integer>> menuButton;

}