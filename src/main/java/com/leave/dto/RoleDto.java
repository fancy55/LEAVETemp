package com.leave.dto;

import com.leave.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto extends Role {
	private List<String> permissionIds;
}
