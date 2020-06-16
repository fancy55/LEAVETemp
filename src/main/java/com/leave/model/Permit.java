package com.leave.model;

import lombok.Data;

import java.util.List;

@Data
public class Permit extends BaseEntity<Long> {
    private String parentId;
    private List<Permit> childId;
    private String name;
    private String css;
    private String href;
    private Integer type;
    private String permission;
    private Integer sort;
}
