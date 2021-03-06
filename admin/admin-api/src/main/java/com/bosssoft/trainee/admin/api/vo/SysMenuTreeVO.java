package com.bosssoft.trainee.admin.api.vo;

import com.bosssoft.trainee.admin.api.model.SysMenu;
import com.bosssoft.trainee.base.common.vo.TreeNodeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("系统菜单树VO")
public class SysMenuTreeVO extends TreeNodeVO<SysMenu> {

    private static final long serialVersionUID = -3356568881250376175L;

    /**
     * 父级菜单id
     */
    @ApiModelProperty("父级菜单id")
    private String parentId;

    /**
     * 菜单类型(0:目录|1:菜单)
     */
    @ApiModelProperty("菜单类型(0:目录|1:菜单)")
    private Integer menuType;

    /**
     * 菜单编码
     */
    @ApiModelProperty("菜单编码")
    private String menuCode;

    /**
     * 菜单标题
     */
    @ApiModelProperty("菜单标题")
    private String menuTitle;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String menuIcon;

    /**
     * 菜单路径(菜单url地址)
     */
    @ApiModelProperty("菜单路径(菜单url地址)")
    private String menuPath;

    /**
     * 页面组件(页面目录地址)
     */
    @ApiModelProperty("页面组件(页面目录地址)")
    private String redirect;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer orderNum;

    /**
     * 是否隐藏(1:隐藏|0:未隐藏)
     */
    @ApiModelProperty("是否隐藏")
    private Integer hidden;

    /**
     * 构建菜单树
     *
     * @param sysMenu 菜单基本信息
     */
    public SysMenuTreeVO(SysMenu sysMenu) {
        this.id = sysMenu.getId();
        this.parentId = sysMenu.getParentId();
        this.menuTitle = sysMenu.getMenuTitle();
        this.menuType = sysMenu.getMenuType();
        this.menuPath = sysMenu.getMenuPath();
        this.orderNum = sysMenu.getOrderNum();
        this.menuCode = sysMenu.getMenuCode();
        this.menuIcon = sysMenu.getMenuIcon();
        this.redirect = sysMenu.getRedirect();
        this.hidden = sysMenu.getHidden();
    }
}