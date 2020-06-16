package com.leave.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("关注他人")
public class Focus {
    @ApiModelProperty(value="关注人身份证",name="idCard",required=true)
    String idCard;
    @ApiModelProperty(value="被关注人身份证",name="publishIdCard",required=true)
    String publishIdCard;
    @ApiModelProperty(value="关注时间",name="date",required=true)
    String date;
}
