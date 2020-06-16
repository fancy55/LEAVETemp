package com.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Restful方式登陆token
 *
 */
@Data
@AllArgsConstructor
public class Token implements Serializable {
	private String token;
	/** 登陆时间戳（毫秒） */
	private Long loginTime;
}
