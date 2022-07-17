package com.bosssoft.trainee.admin.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MenuMetaDTO implements Serializable {

    private static final long serialVersionUID = 4905155582692427492L;
    /**
     * 菜单标题
     */
    @ApiModelProperty("菜单标题")
    private String title;
    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    public MenuMetaDTO() {
    }

    public MenuMetaDTO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}