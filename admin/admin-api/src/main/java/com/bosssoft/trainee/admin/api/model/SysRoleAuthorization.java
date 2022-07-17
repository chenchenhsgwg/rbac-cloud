package com.bosssoft.trainee.admin.api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@TableName("sys_role_authorization")
@ApiModel("系统角色授权")
public class SysRoleAuthorization implements Serializable {

    private static final long serialVersionUID = 3023826693434582074L;

    /**
     * 资源类型: 菜单
     */
    public static final Integer MENU_RESOURCE_TYPE = 0;
    /**
     * 资源类型: 按钮
     */
    public static final Integer BUTTON_RESOURCE_TYPE = 1;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    @TableField("role_code")
    private String roleCode;

    /**
     * 资源id
     */
    @ApiModelProperty("资源id")
    @TableField("resource_id")
    private String resourceId;

    /**
     * 菜单按钮对应的菜单编码
     */
    @ApiModelProperty("菜单按钮对应的菜单编码")
    @TableField("menu_code")
    private String menuCode;

    /**
     * 资源类型（0菜单|1按钮）
     */
    @ApiModelProperty("资源类型（0菜单|1按钮）")
    @TableField("resource_type")
    private Integer resourceType;

    /**
     * 租户Id
     */
    @ApiModelProperty("租户Id")
    @TableField("tenant_id")
    private String tenantId;
}