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
@TableName("base_user_role")
@ApiModel("用户角色关系")
public class BaseUserRole implements Serializable {

    private static final long serialVersionUID = -542985434064954538L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private String userId;
    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    @TableField("role_code")
    private String roleCode;
    /**
     * 租户Id
     */
    @ApiModelProperty("租户Id")
    @TableField("tenant_id")
    private String tenantId;
}