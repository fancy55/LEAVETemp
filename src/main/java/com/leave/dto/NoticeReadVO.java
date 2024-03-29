package com.leave.dto;


import com.leave.model.Notice;
import lombok.Data;

import java.util.Date;

@Data
public class NoticeReadVO extends Notice {

	private static final long serialVersionUID = -3842182350180882396L;

	private Long userId;
	private Date readTime;
	private Boolean isRead;

}
