package com.leave.model;

import lombok.Data;

@Data
public class Mail extends BaseEntity<String> {
	private String userId;
	private String toUsers;
	private String subject;
	private String content;


}
