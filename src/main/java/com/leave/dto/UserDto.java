package com.leave.dto;

import com.leave.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto extends User {

	private List<String> roleIds;
}
