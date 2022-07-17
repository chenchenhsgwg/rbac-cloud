package com.bosssoft.trainee.admin.server.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.trainee.admin.api.model.BaseDeptUser;
import com.bosssoft.trainee.admin.server.mapper.BaseDeptUserMapper;
import com.bosssoft.trainee.base.core.biz.BaseBusinessBiz;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseDeptUserBiz extends BaseBusinessBiz<BaseDeptUserMapper, BaseDeptUser> {

    @Override
    public boolean insertModel(BaseDeptUser entity) {
        return super.insertModel(entity);
    }

    /**
     * 删除部门用户关系
     *
     * @param deptUser 部门用户
     */
    public int delDeptUser(BaseDeptUser deptUser) {
        return this.deleteModel(deptUser);
    }

    /**
     * 查询用户部门编码集合
     *
     * @param userId 用户主键
     * @return 用户的部门关系
     */
    public List<BaseDeptUser> getDeptUserByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<>();
        }
        QueryWrapper<BaseDeptUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return super.list(queryWrapper);
    }

    /**
     * 查询用户部门编码集合
     *
     * @param userId 用户主键
     * @return 用户的部门编码集
     */
    public List<String> getDeptCodesByUserId(String userId) {
        List<String> codes = new ArrayList<>();
        List<BaseDeptUser> deptUserList = this.getDeptUserByUserId(userId);
        for (BaseDeptUser baseDeptUser : deptUserList) {
            codes.add(baseDeptUser.getDeptCode());
        }
        return codes;
    }
}