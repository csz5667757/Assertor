package com.csz.assertor.rest.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;

/**
 * 分页
 */

@ApiModel("分页查询请求")
public class QueryRequest {
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页(从1开始)",example = "1")
    private Integer current;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页记录数(每页最多100条)",example = "5")
    @Max(value = 100, message = "每页最多100条")
    private Integer size;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
