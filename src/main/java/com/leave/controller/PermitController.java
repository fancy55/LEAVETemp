package com.leave.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.leave.annotation.LogAnnotation;
import com.leave.dto.LoginUser;
import com.leave.mapper.PermitDao;
import com.leave.model.Permit;
import com.leave.service.PermitService;
import com.leave.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限相关接口
 *
 */
@Api(tags = "权限")
@RestController
@RequestMapping("/permissions")
public class PermitController {

	@Autowired
	private PermitDao permitDao;
	@Autowired
	private PermitService permissionService;

	@ApiOperation(value = "当前登录用户拥有的权限")
	@GetMapping("/current")
	public List<Permit> permissionsCurrent() {
		LoginUser loginUser = UserUtil.getLoginUser();
		List<Permit> list = loginUser.getPermissions();
		final List<Permit> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());

//		setChild(permissions);
//
//		return permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
		// 2018.06.09 支持多级菜单
        List<Permit> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });

        return firstLevel;
	}

	/**
	 * 设置子元素
	 * 2018.06.09
	 *
	 * @param p
	 * @param permissions
	 */
	private void setChild(Permit p, List<Permit> permissions) {
		List<Permit> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
		p.setChildId(child);
		if (!CollectionUtils.isEmpty(child)) {
			child.parallelStream().forEach(c -> {
				//递归设置子元素，多级菜单支持
				setChild(c, permissions);
			});
		}
	}

//	private void setChild(List<Permission> permissions) {
//		permissions.parallelStream().forEach(per -> {
//			List<Permission> child = permissions.stream().filter(p -> p.getParentId().equals(per.getId()))
//					.collect(Collectors.toList());
//			per.setChild(child);
//		});
//	}

	/**
	 * 菜单列表
	 *
	 * @param pId
	 * @param permissionsAll
	 * @param list
	 */
	private void setPermissionsList(Long pId, List<Permit> permissionsAll, List<Permit> list) {
		for (Permit per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				list.add(per);
				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					setPermissionsList(per.getId(), permissionsAll, list);
				}
			}
		}
	}

	@GetMapping
	@ApiOperation(value = "菜单列表")
	@PreAuthorize("hasAuthority('menu:query')")
	public List<Permit> permissionsList() {
		List<Permit> permissionsAll = permitDao.listAll();

		List<Permit> list = Lists.newArrayList();
		setPermissionsList(0L, permissionsAll, list);

		return list;
	}

	@GetMapping("/all")
	@ApiOperation(value = "所有菜单")
	@PreAuthorize("hasAuthority('menu:query')")
	public JSONArray permissionsAll() {
		List<Permit> permissionsAll = permitDao.listAll();
		JSONArray array = new JSONArray();
		setPermissionsTree(0L, permissionsAll, array);

		return array;
	}

	@GetMapping("/parents")
	@ApiOperation(value = "一级菜单")
	@PreAuthorize("hasAuthority('menu:query')")
	public List<Permit> parentMenu() {
		List<Permit> parents = permitDao.listParents();

		return parents;
	}

	/**
	 * 菜单树
	 *
	 * @param pId
	 * @param permissionsAll
	 * @param array
	 */
	private void setPermissionsTree(Long pId, List<Permit> permissionsAll, JSONArray array) {
		for (Permit per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				String string = JSONObject.toJSONString(per);
				JSONObject parent = (JSONObject) JSONObject.parse(string);
				array.add(parent);

				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					JSONArray child = new JSONArray();
					parent.put("child", child);
					setPermissionsTree(per.getId(), permissionsAll, child);
				}
			}
		}
	}

	@GetMapping(params = "roleId")
	@ApiOperation(value = "根据角色id获取权限")
	@PreAuthorize("hasAnyAuthority('menu:query','role:query')")
	public List<Permit> listByRoleId(String roleId) {
		return permitDao.listByRoleId(roleId);
	}

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存菜单")
	@PreAuthorize("hasAuthority('menu:add')")
	public void save(@RequestBody Permit permission) {
		permitDao.save(permission);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据菜单id获取菜单")
	@PreAuthorize("hasAuthority('menu:query')")
	public Permit get(@PathVariable String id) {
		return permitDao.getById(id);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改菜单")
	@PreAuthorize("hasAuthority('menu:add')")
	public void update(@RequestBody Permit permission) {
		permissionService.update(permission);
	}

	/**
	 * 校验权限
	 *
	 * @return
	 */
	@GetMapping("/owns")
	@ApiOperation(value = "校验当前用户的权限")
	public Set<String> ownsPermission() {
		List<Permit> permissions = UserUtil.getLoginUser().getPermissions();
		if (CollectionUtils.isEmpty(permissions)) {
			return Collections.emptySet();
		}

		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(Permit::getPermission).collect(Collectors.toSet());
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除菜单")
	@PreAuthorize("hasAuthority('menu:del')")
	public void delete(@PathVariable String id) {
		permissionService.delete(id);
	}
}
