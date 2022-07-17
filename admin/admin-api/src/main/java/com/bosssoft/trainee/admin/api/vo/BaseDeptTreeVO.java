package com.bosssoft.trainee.admin.api.vo;

import com.bosssoft.trainee.admin.api.model.BaseDept;
import com.bosssoft.trainee.base.common.vo.TreeNodeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@ApiModel("部门树vo")
public class BaseDeptTreeVO extends TreeNodeVO<BaseDept> {

    private static final long serialVersionUID = -4121445467608449L;

    /**
     * 部门编码
     */
    @ApiModelProperty("部门编码")
    private String deptCode;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String deptName;

    /**
     * 最后更新日期
     */
    @ApiModelProperty("最后更新日期")
    private LocalDateTime updateTime;

    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;

    /**
     * 构造部门树
     *
     * @param baseDept 部门信息
     */
    public BaseDeptTreeVO(BaseDept baseDept) {
        this.id = baseDept.getId();
        this.parentId = baseDept.getParentId();
        this.deptCode = baseDept.getDeptCode();
        this.deptName = baseDept.getDeptName();
        this.updateTime = baseDept.getUpdateTime();
        this.createTime = baseDept.getCreateTime();
    }
}