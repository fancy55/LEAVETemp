package com.leave.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("对本程序的意见")
public class Feedback {
    @ApiModelProperty(value="电话号码",name="phone",required=true)
    private String phone;
    @ApiModelProperty(value="反馈内容",name="content",required=true)
    private String content;
    @ApiModelProperty(value="反馈时间",name="date",required=true)
    private String date;
}
