package com.leave.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("对商品点赞")
public class LikeGoods {
    @ApiModelProperty(value="点赞人身份证",name="idCard",required=true)
    String idCard;
    @ApiModelProperty(value="发布人身份证",name="publishIdCard",required=true)
    String publishIdCard;
    @ApiModelProperty(value="发布人发布时间",name="pDate",required=true)
    String pDate;
    @ApiModelProperty(value="点赞时间",name="date",required=true)
    String date;
}
