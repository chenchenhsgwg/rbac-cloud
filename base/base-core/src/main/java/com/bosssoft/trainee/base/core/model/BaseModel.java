package com.bosssoft.trainee.base.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@ApiModel("基础Model类")
public class BaseModel {
    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;
    /**
     * 创建用户Id
     */
    @ApiModelProperty("创建用户Id")
    private String createUserId;
    /**
     * 创建用户姓名
     */
    @ApiModelProperty("创建用户姓名")
    private String createUserName;
    /**
     * 最后更新日期
     */
    @ApiModelProperty("最后更新日期")
    private LocalDateTime updateTime;
    /**
     * 最后更新用户Id
     */
    @ApiModelProperty("最后更新用户Id")
    private String updateUserId;
    /**
     * 最后更新用户姓名
     */
    @ApiModelProperty("最后更新用户姓名")
    private String updateUserName;
    /**
     * 租户Id
     */
    @ApiModelProperty("租户Id")
    private String tenantId;
}