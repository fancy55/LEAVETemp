package com.leave.controller;

import com.leave.mapper.LogsDao;
import com.leave.model.Logs;
import com.leave.table.PageTableHandler;
import com.leave.table.PageTableRequest;
import com.leave.table.PageTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "日志")
@RestController
@RequestMapping("/logs")
public class LogsController {

	@Autowired
	private LogsDao logsDao;

	@GetMapping
	@PreAuthorize("hasAuthority('log:query')")
	@ApiOperation(value = "日志列表")
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return logsDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<Logs> list(PageTableRequest request) {
				return logsDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

}
