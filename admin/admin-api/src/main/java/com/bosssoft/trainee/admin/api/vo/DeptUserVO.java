package com.bosssoft.trainee.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("部门用户关系")
public class DeptUserVO {
    /**
     * 用户ids
     */
    @ApiModelProperty("用户ids")
    public List<String> userIds;
}