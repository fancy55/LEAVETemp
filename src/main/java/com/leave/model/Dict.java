package com.leave.model;

import lombok.Data;

@Data
public class Dict extends BaseEntity<Long> {
    private String type;
    private String k;
    private String val;
}
