package com.bosssoft.trainee.base.core.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("响应单个对象")
public class ObjectResult<T> extends Result {

    @ApiModelProperty("响应对象")
    private T data;

    public ObjectResult() {
    }

    public ObjectResult(String message) {
        super(message);
    }
}