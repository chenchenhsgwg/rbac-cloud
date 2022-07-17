package com.bosssoft.trainee.admin.api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bosssoft.trainee.base.core.model.BaseModel;
import com.bosssoft.trainee.base.common.validate.AddField;
import com.bosssoft.trainee.base.common.validate.UpdateField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@TableName("sys_menu_button")
@ApiModel("系统基础菜单按钮")
public class SysMenuButton extends BaseModel implements Serializable {

    private static final long serialVersionUID = -6770277094809446227L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    @NotNull(groups = UpdateField.class)
    private Integer id;

    /**
     * 菜单id
     */
    @ApiModelProperty("菜单id")
    @NotBlank(groups = {AddField.class, UpdateField.class})
    @Size(max = 64, groups = {AddField.class, UpdateField.class})
    @TableField("menu_id")
    private String menuId;

    /**
     * 按钮编码
     */
    @ApiModelProperty("按钮编码")
    @NotBlank(groups = {AddField.class, UpdateField.class})
    @Size(max = 128, groups = {AddField.class, UpdateField.class})
    @TableField("button_code")
    private String buttonCode;

    /**
     * 按钮标题
     */
    @ApiModelProperty("按钮标题")
    @NotBlank(groups = {AddField.class, UpdateField.class})
    @Size(max = 64, groups = {AddField.class, UpdateField.class})
    @TableField("button_title")
    private String buttonTitle;

    /**
     * 请求地址
     */
    @ApiModelProperty("请求地址")
    @NotBlank(groups = {AddField.class, UpdateField.class})
    @Size(max = 256, groups = {AddField.class, UpdateField.class})
    @TableField("url")
    private String url;

    /**
     * 请求方式（POST|GET|DELETE|PUT）
     */
    @ApiModelProperty("请求方式（POST|GET|DELETE|PUT）")
    @NotBlank(groups = {AddField.class, UpdateField.class})
    @Size(max = 12, groups = {AddField.class, UpdateField.class})
    @TableField("method")
    private String method;

    /**
     * 菜单描述
     */
    @ApiModelProperty("菜单描述")
    @TableField("description")
    private String description;

    /**
     * 是否删除(1:删除|0:未删除)
     */
    @ApiModelProperty("是否删除(1:删除|0:未删除)")
    @TableField("is_deleted")
    private Integer isDeleted;
}