package com.bosssoft.trainee.admin.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MenuButtonCodeDTO implements Serializable {

    private static final long serialVersionUID = -3653332667008246004L;
    /**
     * 菜单编码
     */
    @ApiModelProperty("菜单编码")
    private String menuCode;
    /**
     * 按钮编码
     */
    @ApiModelProperty("按钮编码")
    private String buttonCode;
}