package com.bosssoft.trainee.admin.server.rest;

import com.bosssoft.trainee.admin.api.model.BaseDept;
import com.bosssoft.trainee.admin.api.vo.BaseDeptTreeVO;
import com.bosssoft.trainee.admin.api.vo.DeptUserVO;
import com.bosssoft.trainee.admin.server.biz.BaseDeptBiz;
import com.bosssoft.trainee.auth.client.annotation.CheckAuthToken;
import com.bosssoft.trainee.base.common.constant.HttpStatusConstant;
import com.bosssoft.trainee.base.core.result.ObjectResult;
import com.bosssoft.trainee.base.core.rest.BaseController;
import com.bosssoft.trainee.base.common.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CheckAuthToken
@RestController
@RequestMapping("/baseDepts")
@Api(tags = "基础部门信息表")
public class BaseDeptController extends BaseController<BaseDeptBiz, BaseDept> {

    @GetMapping("/tree")
    @ApiOperation(value = "获取部门树")
    public ObjectResult<List<BaseDeptTreeVO>> getBaseDeptTree() {
        ObjectResult<List<BaseDeptTreeVO>> result = new ObjectResult<>();
        result.setData(this.baseBiz.getBaseDeptTree());
        return result;
    }

    /**
     * 添加部门用户关系
     *
     * @param deptCode 部门编码
     * @param deptUser 用户集合
     */
    @PutMapping("/deptUsers/{deptCode}")
    @ApiOperation("添加部门用户关系")
    public ObjectResult addDeptUser(@PathVariable("deptCode") @ApiParam("部门编码") String deptCode,
                                    @RequestBody DeptUserVO deptUser) {
        ObjectResult result = new ObjectResult();
        List<String> userIds = deptUser.getUserIds();
        if (BeanUtil.isEmpty(userIds)) {
            result.setMessage("请选择待分配的用户！");
            result.setStatus(HttpStatusConstant.FAIL);
            return result;
        }
        this.baseBiz.addDeptUser(deptCode, userIds);
        return result;
    }

    /**
     * 删除部门的用户关系
     *
     * @param deptCode 部门编码
     * @param userId   用户编码
     */
    @DeleteMapping("/deptUsers/{deptCode}/userId/{userId}")
    @ApiOperation("删除部门的用户关系")
    public ObjectResult delDeptUserByUserId(@PathVariable("deptCode") @ApiParam("部门编码") String deptCode,
                                            @PathVariable("userId") @ApiParam("用户主键") String userId) {
        ObjectResult result = new ObjectResult();
        this.baseBiz.delDeptUserByUserId(deptCode, userId);
        return result;
    }
}