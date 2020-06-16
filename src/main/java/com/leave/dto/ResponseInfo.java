package com.leave.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseInfo implements Serializable {

	private String code;
	private String message;

	public ResponseInfo(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
