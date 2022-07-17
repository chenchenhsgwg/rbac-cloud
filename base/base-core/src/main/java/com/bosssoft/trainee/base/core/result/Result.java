package com.bosssoft.trainee.base.core.result;

import com.bosssoft.trainee.base.common.constant.HttpStatusConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@ApiModel("响应基类")
public class Result {
    /**
     * 响应码
     */
    @ApiModelProperty("响应状态码")
    private int status = HttpStatusConstant.SUCCESS;
    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String message;

    /**
     * 返回结果附属信息
     */
    @ApiModelProperty("返回结果附属信息")
    private Map<String, Object> metadata;

    public Result() {
    }

    public Result(String message) {
        this.message = message;
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }
}