package com.leave.model;

import lombok.Data;

@Data
public class Role extends BaseEntity<String> {
    private String name;
    private String description;
}
