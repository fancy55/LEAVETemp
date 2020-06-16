package com.leave.model;

import lombok.Data;

@Data
public class FileInfo extends BaseEntity<String> {
    private String contentType;
    private long size;
    private String path;
    private String url;
    private Integer type;
}
