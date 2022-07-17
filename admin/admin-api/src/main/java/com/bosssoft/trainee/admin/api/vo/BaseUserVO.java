package com.bosssoft.trainee.admin.api.vo;

import com.bosssoft.trainee.admin.api.model.BaseUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@ApiModel("用户信息vo")
public class BaseUserVO extends BaseUser {

    private static final long serialVersionUID = 1328369755045511388L;
    /**
     * 新密码
     */
    @ApiModelProperty("新密码")
    private String newPassword;

    /**
     * 角色编码集合
     */
    @ApiModelProperty("角色编码集合")
    private List<String> roleCodes;
}