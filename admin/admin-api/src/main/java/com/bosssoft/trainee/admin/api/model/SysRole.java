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
@TableName("sys_role")
@ApiModel("系统角色")
public class SysRole extends BaseModel implements Serializable {

    private static final long serialVersionUID = 146560826438800043L;

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
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    @TableField("role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @TableField("role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty("角色描述")
    @TableField("description")
    private String description;

    /**
     * 是否删除（1是|0否）
     */
    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;
}