package com.leave.model;

import lombok.Data;
import java.util.Date;

@Data
public class TokenModel extends BaseEntity<String> {

    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * LoginUser的json串
     */
    private String val;


}
