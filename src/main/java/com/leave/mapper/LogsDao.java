package com.leave.mapper;

import com.leave.model.Logs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogsDao {

	@Insert("insert into sys_logs(userId, module, flag, remark, createTime) values(#{user.id}, #{module}, #{flag}, #{remark}, now())")
	int save(Logs sysLogs);

	int count(@Param("params") Map<String, Object> params);

	List<Logs> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
					@Param("limit") Integer limit);

	@Delete("delete from sys_logs where createTime <= #{time}")
	int deleteLogs(String time);
}
