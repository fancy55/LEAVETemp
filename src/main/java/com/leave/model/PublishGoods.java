package com.leave.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("发布商品")
public class PublishGoods implements Serializable {
    @ApiModelProperty(value="商品名称",name="name",required=true)
    String name;
    @ApiModelProperty(value="商品价格",name="price",required=true)
    String price;
    @ApiModelProperty(value="商品描述",name="description",required=true)
    String description;
    @ApiModelProperty(value="商品购买状态",name="status",required=true)
    String status;
    @ApiModelProperty(value="商品分类",name="category",example = "book/cosmetics/clothing/sporting/planting/games/instrument/ticket/household/digital/others",required=true)
    String category;
    @ApiModelProperty(value="发布人昵称",name="nickName",required=true)
    String nickName;
    @ApiModelProperty(value="发布人身份证",name="publishIdCard",required=true)
    String publishIdCard;
    @ApiModelProperty(value="发布人发布时间",name="pDate",required=true)
    String pDate;
    @ApiModelProperty(value="买家身份证",name="buyerIdCard",required=true)
    String buyerIdCard;
    @ApiModelProperty(value="交易地址",name="address",required=true)
    String address;
    @ApiModelProperty(value="交易时间",name="date",required=true)
    String date;
    @ApiModelProperty(value="商品图片1",name="photo1",required=true)
    String photo1;
    @ApiModelProperty(value="商品图片2",name="photo2",required=true)
    String photo2;
    @ApiModelProperty(value="商品图片3",name="photo3",required=true)
    String photo3;
    @ApiModelProperty(value="商品图片4",name="photo4",required=true)
    String photo4;
    @ApiModelProperty(value="商品图片5",name="photo5",required=true)
    String photo5;
    @ApiModelProperty(value="商品图片6",name="photo6",required=true)
    String photo6;
    @ApiModelProperty(value="发布人头像",name="photo",required=true)
    String photo;
}
