package com.leave.dto;

import com.leave.model.Notice;
import com.leave.model.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NoticeVO implements Serializable {

	private static final long serialVersionUID = 7363353918096951799L;

	private Notice notice;

	private List<User> users;

}
