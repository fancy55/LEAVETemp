package com.leave.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("取消交易")
public class Cancel {
    @ApiModelProperty(value="买家身份证",name="buyerIdCard",required=true)
    String buyerIdCard;
    @ApiModelProperty(value="发布人身份证",name="publishIdCard",required=true)
    String publishIdCard;
    @ApiModelProperty(value="发布人发布时间",name="pDate",required=true)
    String pDate;
    @ApiModelProperty(value="购买时间",name="date",required=true)
    String date;
    @ApiModelProperty(value="取消人的身份证",name="cancelMan",required=true)
    String cancelMan;
}
