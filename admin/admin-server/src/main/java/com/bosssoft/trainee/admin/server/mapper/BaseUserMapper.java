package com.bosssoft.trainee.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.admin.api.model.BaseUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseUserMapper extends BaseMapper<BaseUser> {
    /**
     * 通过部门编码获取用户列表
     *
     * @param deptCode 部门编码
     * @return 指定部门的用户列表
     */
    List<BaseUser> selectDeptUsersByDeptCode(@Param("deptCode") String deptCode);

    /**
     * 排除指定部门的用户列表
     *
     * @param excludeDeptCode 排除指定部门
     * @return 获取所有部门用户列表
     */
    List<BaseUser> selectUsersExcludeDept(@Param("excludeDeptCode") String excludeDeptCode);
}