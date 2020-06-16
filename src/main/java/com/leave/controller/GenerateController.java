package com.leave.controller;

import com.leave.annotation.LogAnnotation;
import com.leave.dto.BeanField;
import com.leave.dto.GenerateDetail;
import com.leave.dto.GenerateInput;
import com.leave.service.GenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成接口
 *
 * @author 小威老师 xiaoweijiagou@163.com
 *
 */
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {

	@Autowired
	private GenerateService generateService;

	@ApiOperation("根据表名显示表信息")
	@GetMapping(params = { "tableName" })
	@PreAuthorize("hasAuthority('generate:edit')")
	public GenerateDetail generateByTableName(String tableName) {
		GenerateDetail detail = new GenerateDetail();
		detail.setBeanName(generateService.upperFirstChar(tableName));
		List<BeanField> fields = generateService.listBeanField(tableName);
		detail.setFields(fields);

		return detail;
	}

	@LogAnnotation
	@ApiOperation("生成代码")
	@PostMapping
	@PreAuthorize("hasAuthority('generate:edit')")
	public void save(@RequestBody GenerateInput input) {
		generateService.saveCode(input);
	}

}
