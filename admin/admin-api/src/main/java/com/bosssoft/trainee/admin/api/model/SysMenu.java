package com.bosssoft.trainee.admin.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bosssoft.trainee.base.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableName("sys_menu")
@ApiModel("系统菜单")
public class SysMenu extends BaseModel implements Serializable {

    private static final long serialVersionUID = 5214370127850298525L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId
    private String id;

    /**
     * 父级菜单id
     */
    @ApiModelProperty("父级菜单id")
    @TableField("parent_id")
    private String parentId;

    /**
     * 菜单类型(0:目录|1:菜单)
     */
    @ApiModelProperty("菜单类型(0:目录|1:菜单)")
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 菜单编码
     */
    @ApiModelProperty("菜单编码")
    @TableField("menu_code")
    private String menuCode;

    /**
     * 菜单标题
     */
    @ApiModelProperty("菜单标题")
    @TableField("menu_title")
    private String menuTitle;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    @TableField("menu_icon")
    private String menuIcon;

    /**
     * 菜单路径（路径别名）
     */
    @ApiModelProperty("菜单路径（路径别名）")
    @TableField("menu_path")
    private String menuPath;

    /**
     * 菜单路径(菜单url地址)
     */
    @ApiModelProperty("菜单路径(菜单url地址)")
    @TableField("component")
    private String component;

    /**
     * 页面组件(页面目录地址)
     */
    @ApiModelProperty("页面组件(页面目录地址)")
    @TableField("redirect")
    private String redirect;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 菜单描述
     */
    @ApiModelProperty("菜单描述")
    @TableField("description")
    private String description;

    /**
     * 是否删除(1:删除|0:未删除)
     */
    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 是否隐藏(1:隐藏|0:未隐藏)
     */
    @ApiModelProperty("是否隐藏(1:隐藏|0:未隐藏)")
    @TableField("hidden")
    private Integer hidden;
}