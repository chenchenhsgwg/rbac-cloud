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
@TableName("base_dept_user")
@ApiModel("基础角色部门")
public class BaseDeptUser implements Serializable {

    private static final long serialVersionUID = -5230911663453074853L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;
    /**
     * 部门编码
     */
    @ApiModelProperty("部门编码")
    @TableField("dept_code")
    private String deptCode;
    /**
     * 租户Id
     */
    @ApiModelProperty("租户Id")
    @TableField("tenant_id")
    private String tenantId;
}