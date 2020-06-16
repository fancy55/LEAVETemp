package com.leave.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("对商品留言")
public class Leave {
    @ApiModelProperty(value="用户身份证",name="idCard",required=true)
    String idCard;
    @ApiModelProperty(value="发布人身份证",name="publishIdCard",required=true)
    String publishIdCard;
    @ApiModelProperty(value="发布人发布时间",name="pDate",required=true)
    String pDate;
    @ApiModelProperty(value="留言内容",name="content",required=true)
    String content;
    @ApiModelProperty(value="留言时间",name="date",required=true)
    String date;
    @ApiModelProperty(value="留言人身份证",name="personID",required=true)
    String personID;
    @ApiModelProperty(value="被留言人昵称",name="pNickname",required=true)
    String pNickname;
    @ApiModelProperty(value="留言人昵称",name="nickname",required=true)
    String nickname;
    @ApiModelProperty(value="留言被查看的状态",name="status",required=true)
    String status;
    @ApiModelProperty(value="主留言人时间,对商品留言时可为空，对某个人留言填上那个人的对商品留言的时间·",name="idDate",required=true)
    String idDate;
}
