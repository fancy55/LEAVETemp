package com.leave.model;

import lombok.Data;

@Data
public class Notice extends BaseEntity<String> {
	private String title;
	private String content;
	private Integer status;

	public interface Status {
		int DRAFT = 0;
		int PUBLISH = 1;
	}

}
