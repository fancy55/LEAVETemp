package com.leave.model;

import lombok.Data;

@Data
public class Logs extends BaseEntity<Long> {

	private static final long serialVersionUID = -7809315432127036583L;
	private User user;
	private String module;
	private Boolean flag;
	private String remark;

}
