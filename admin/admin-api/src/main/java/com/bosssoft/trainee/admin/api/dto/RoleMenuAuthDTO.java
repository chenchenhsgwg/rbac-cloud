package com.bosssoft.trainee.admin.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleMenuAuthDTO {
    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    private String roleCode;
    /**
     * 请求地址
     */
    @ApiModelProperty("请求地址")
    private String url;
    /**
     * 请求方式
     */
    @ApiModelProperty("请求方式")
    private String method;
}