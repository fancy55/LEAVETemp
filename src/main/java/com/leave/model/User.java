package com.leave.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Api(value="User对象",description = "用户信息表")
public class User extends BaseEntity<String> implements Serializable {
    @ApiModelProperty(name = "nick",dataType = "String",value = "昵称len<=15")
    @Size(max=15,min=1)
    private String nick;

    @ApiModelProperty(name = "phone",dataType = "String",value = "电话号码len=11")
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号格式错误")
    @NotEmpty(message = "电话号码不正确") //不为null且不为空（字符串长度不为0、集合大小不为0）
    private String phone;

    @ApiModelProperty(name = "pswd",dataType = "String",value = "密码")
    @Size(max=20,min=8)
    @NotEmpty(message = "密码长度需>=8 <=20")
    private String pswd;//不符合规范

    @ApiModelProperty(name = "idx",dataType = "String",value = "对应电话号码和密码的编码")//第一次注册就生成唯一，以后无法再更改
    @Size(max=15,min=1)
    private String idx;//唯一

    @ApiModelProperty(name = "card",dataType = "String",value = "身份证")
    private String card;

    @ApiModelProperty(name = "name",dataType = "String",value = "真实姓名")
    @Size(max=45,min=2)
    private String name;

    @ApiModelProperty(name = "age",dataType = "Integer",value = "年龄")
    @Max(150)
    @Min(0)
    private Integer age;

    @ApiModelProperty(name = "sex",dataType = "Character",value = "性别", example = "F/M")
    private Character sex;

    @ApiModelProperty(name = "bg",dataType = "String",value = "背景图片")
    private String bg;

    @ApiModelProperty(name = "photo",dataType = "String",value = "头像")
    private String photo;

    @ApiModelProperty(name = "status",dataType = "Character",value = "用户状态",example = "1:正常；0:异常封号；2：注销")
    private Character status;

    @ApiModelProperty(name = "qq",dataType = "String",value = "qq第三方")
    private String qq;

    @ApiModelProperty(name = "wx",dataType = "String",value = "微信第三方")
    private String wx;

    @ApiModelProperty(name = "github",dataType = "String",value = "github第三方")
    private String github;

    @ApiModelProperty(name = "mail",dataType = "String",value = "邮件第三方")
    @Email(message = "邮箱不正确")
    private String mail;

    @ApiModelProperty(name = "score",dataType = "String",value = "积分")
    private Integer score;

    @ApiModelProperty(name = "addr",dataType = "String",value = "地址")
    private String addr;

    @ApiModelProperty(name = "date",dataType = "Date",value = "注册时间")
    private Date date;

    @ApiModelProperty(name = "ltime",dataType = "Date",value = "最后一次登录下线时间")
    private Date ltime;

    @ApiModelProperty(name = "descri",dataType = "String",value = "个性签名")
    @Size(min=0,max=45)
    private String descri;

    public interface Status {
        char DISABLED = '0';
        char VALID = '1';
        char LOCKED = '2';
    }
}
