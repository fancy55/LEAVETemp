package com.leave.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HotSearch {
    @ApiModelProperty(value="搜索内容",name="name",required=true)
    String name;
    @ApiModelProperty(value="搜索次数",name="cnt",required=true)
    Integer cnt;
}
