package com.bosssoft.trainee.admin.api.vo;

import com.bosssoft.trainee.admin.api.model.SysRoleAuthorization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("角色授权vo")
public class RoleAuthorizationVO extends SysRoleAuthorization {

    private static final long serialVersionUID = 3989082080759444669L;

    /**
     * 菜单按钮对应的菜单编码
     */
    @ApiModelProperty("菜单按钮对应的菜单编码")
    private String menuCode;

    /**
     * 关联资源集合
     */
    @ApiModelProperty("关联资源集合")
    private List<String> resourceIds;
}