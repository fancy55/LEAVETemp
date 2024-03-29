package com.leave.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    private ID id;
    private Date createTime = new Date();
    private Date updateTime = new Date();
}