package com.bosssoft.trainee.base.common.vo;

import com.bosssoft.trainee.base.common.constant.CommonConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
public class ParamQuery extends LinkedHashMap<String, Object> {

    /**
     * 默认页码
     */
    private int page = 1;

    /**
     * 默认每页条数
     */
    private int limit = 10;

    public ParamQuery(Map<String, Object> params) {
        // 设置查询参数
        this.putAll(params);


        // 获取请求参数中的页码
        Object page = params.get(CommonConstant.Page.PAGE);
        if (null != page) {
            this.page = Integer.parseInt(page.toString());
        }

        // 获取请求参数中的页容量，如果超过200则替换为200
        Object limit = params.get(CommonConstant.Page.LIMIT);
        if (null != limit) {
            this.limit = Integer.parseInt(limit.toString());
            if (this.limit > CommonConstant.LIMIT_MAX) {
                this.limit = CommonConstant.LIMIT_MAX;
            }
        }

        // 删除多余的分页查询参数
        this.remove(CommonConstant.Page.PAGE);
        this.remove(CommonConstant.Page.LIMIT);
    }

    /**
     * 初始化时优化分页参数
     */
    public ParamQuery() {
        // 获取请求参数中的页码
        Object page = this.get(CommonConstant.Page.PAGE);
        if (null != page) {
            this.page = Integer.parseInt(page.toString());
        }

        // 获取请求参数中的页容量，如果超过200则替换为200
        Object limit = this.get(CommonConstant.Page.LIMIT);
        if (null != limit) {
            this.limit = Integer.parseInt(limit.toString());
            if (this.limit > CommonConstant.LIMIT_MAX) {
                this.limit = CommonConstant.LIMIT_MAX;
            }
        }

        // 删除多余的分页查询参数
        this.remove(CommonConstant.Page.PAGE);
        this.remove(CommonConstant.Page.LIMIT);
    }
}