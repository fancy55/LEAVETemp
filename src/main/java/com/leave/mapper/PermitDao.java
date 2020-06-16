package com.leave.mapper;

import com.leave.model.Permit;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermitDao {

	@Select("select * from sys_permission t order by t.sort")
	List<Permit> listAll();

	@Select("select * from sys_permission t where t.type = 1 order by t.sort")
	List<Permit> listParents();

	@Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = #{userId} order by p.sort")
	List<Permit> listByUserId(String userId);

	@Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
	List<Permit> listByRoleId(String roleId);

	@Select("select * from sys_permission t where t.id = #{id}")
	Permit getById(String id);

	@Insert("insert into sys_permission(parentId, name, css, href, type, permission, sort) values(#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort})")
	int save(Permit permission);

	@Update("update sys_permission t set parentId = #{parentId}, name = #{name}, css = #{css}, href = #{href}, type = #{type}, permission = #{permission}, sort = #{sort} where t.id = #{id}")
	int update(Permit permission);

	@Delete("delete from sys_permission where id = #{id}")
	int delete(String id);

	@Delete("delete from sys_permission where parentId = #{id}")
	int deleteByParentId(String id);

	@Delete("delete from sys_role_permission where permissionId = #{permissionId}")
	int deleteRolePermission(String permissionId);

	@Select("select ru.userId from sys_role_permission rp inner join sys_role_user ru on ru.roleId = rp.roleId where rp.permissionId = #{permissionId}")
	Set<String> listUserIds(String permissionId);
}
