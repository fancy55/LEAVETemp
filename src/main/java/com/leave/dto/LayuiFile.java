package com.leave.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LayuiFile implements Serializable {

	private Integer code;
	private String msg;
	private LayuiFileData data;

	@Data
	public static class LayuiFileData implements Serializable {

		private String src;
		private String title;

	}
}
