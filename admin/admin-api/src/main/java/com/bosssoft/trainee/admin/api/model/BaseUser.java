package com.bosssoft.trainee.admin.api.model;

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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("基础用户")
@TableName("base_user")
public class BaseUser extends BaseModel implements Serializable {

    private static final long serialVersionUID = 8660668742160058584L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId
    @NotBlank(groups = UpdateField.class)
    @Size(min = 1, max = 64, groups = UpdateField.class)
    private String id;

    /**
     * 用户账户
     */
    @ApiModelProperty("用户账户")
    @NotBlank(groups = {AddField.class, UpdateField.class})
    @Size(min = 3, max = 64, groups = {AddField.class, UpdateField.class})
    @TableField("username")
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    @NotBlank(groups = AddField.class)
    @Size(max = 256, groups = AddField.class)
    @TableField("password")
    private String password;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    @TableField("name")
    private String name;

    /**
     * 用户性别
     */
    @ApiModelProperty("用户性别")
    @TableField("user_sex")
    private Integer userSex;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    @TableField("portrait")
    private String portrait;

    /**
     * 用户生日
     */
    @ApiModelProperty("用户生日")
    @TableField("birthday")
    private LocalDateTime birthday;

    /**
     * 用户地址
     */
    @ApiModelProperty("用户地址")
    @TableField("address")
    private String address;

    /**
     * 用户手机号
     */
    @ApiModelProperty("用户手机号")
    @TableField("mobile_phone")
    private String mobilePhone;

    /**
     * 用户电话号
     */
    @ApiModelProperty("用户电话号")
    @TableField("tel_phone")
    private String telPhone;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    @TableField("user_email")
    private String userEmail;

    /**
     * 用户说明
     */
    @ApiModelProperty("用户说明")
    @TableField("description")
    private String description;

    /**
     * 是否删除（1是|0否）
     */
    @ApiModelProperty("是否删除（1是|0否）")
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 是否禁用（1是|0否）
     */
    @ApiModelProperty("是否禁用（1是|0否）")
    @TableField("is_disabled")
    private Integer isDisabled;

    /**
     * 是否超级管理员（1是|0否）
     */
    @ApiModelProperty("是否超级管理员（1是|0否）")
    @TableField("is_super_admin")
    private Integer isSuperAdmin;

}