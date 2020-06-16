package com.leave.model;

import lombok.Data;

@Data
public class MailTo extends BaseEntity<String> {

	private static final long serialVersionUID = -8238779033956731073L;

	private String mailId;
	private String toUser;
	private Boolean status;

}
