package com.bosssoft.trainee.admin.api.vo;

import com.bosssoft.trainee.admin.api.model.SysRole;
import com.bosssoft.trainee.base.common.vo.TreeNodeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("角色树VO")
public class SysRoleTreeVO extends TreeNodeVO<SysRole> {

    private static final long serialVersionUID = -2709582128741679872L;

    /**
     * 角色编码
     */
    @ApiModelProperty("角色编码")
    private String roleCode;

    /**
     * 角色描述
     */
    @ApiModelProperty("角色描述")
    private String description;

    /**
     * 构造角色树
     *
     * @param id          角色id
     * @param parentId    角色父级id
     * @param roleCode    角色编码
     * @param roleName    角色名称
     * @param description 角色说明
     */
    public SysRoleTreeVO(Object id, Object parentId, String roleCode, String roleName, String description) {
        this.id = id;
        this.parentId = parentId;
        this.roleCode = roleCode;
        this.label = roleName;
        this.description = description;
    }
}