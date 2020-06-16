package com.leave.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("举报商品")
public class Report {
    @ApiModelProperty(value="举报人身份证",name="reportIdCard",required=true)
    String reportIdCard;
    @ApiModelProperty(value="发布人身份证",name="publishIdCard",required=true)
    String publishIdCard;
    @ApiModelProperty(value="发布人发布时间",name="pDate",required=true)
    String pDate;
    @ApiModelProperty(value="举报内容",name="content",required=true)
    String content;
    @ApiModelProperty(value="举报状态",name="status",required=true)
    String status;
    @ApiModelProperty(value="举报时间",name="date",required=true)
    String date;
}
