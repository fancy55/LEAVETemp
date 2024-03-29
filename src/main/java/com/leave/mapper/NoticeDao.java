package com.leave.mapper;

import com.leave.dto.NoticeReadVO;
import com.leave.model.Notice;
import com.leave.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeDao {

	@Select("select * from t_notice t where t.id = #{id}")
	Notice getById(String id);

	@Delete("delete from t_notice where id = #{id}")
	int delete(String id);

	@Update("update t_notice t set title = #{title}, content = #{content}, status = #{status}, updateTime = #{updateTime} where t.id = #{id}")
	int update(Notice notice);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into t_notice(title, content, status, createTime, updateTime) values(#{title}, #{content}, #{status}, #{createTime}, #{updateTime})")
	int save(Notice notice);

	int count(@Param("params") Map<String, Object> params);

	List<Notice> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);

	@Insert("insert ignore into t_notice_read(noticeId, userId, createTime) values(#{noticeId}, #{userId}, now())")
	int saveReadRecord(@Param("noticeId") String noticeId, @Param("userId") String userId);

	List<User> listReadUsers(String noticeId);

	@Select("select count(1) from t_notice t left join t_notice_read r on r.noticeId = t.id and r.userId = #{userId} where t.status = 1 and r.userId is null")
	int countUnread(String userId);

	int countNotice(@Param("params") Map<String, Object> params);

	List<NoticeReadVO> listNotice(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
								  @Param("limit") Integer limit);
}
