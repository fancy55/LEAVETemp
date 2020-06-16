package com.leave.mapper;

import com.leave.mapper.imp.UserImpl;
import com.leave.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserDao extends UserImpl {
    @Insert("insert into user(phone,pswd,status,date,photo,score,idx) values(#{phone},MD5(#{pswd}),'1',now(),'http://47.104.191.228:8089/lev/default.png','0',#{idx})")
    void register(@Param("phone")String phone, @Param("idx") String idx, @Param("pswd")String pswd);

    @Select("select idx from user where phone = #{phone} and pswd = #{pswd}")
    String login(@Param("phone")String phone, @Param("pswd")String pswd);

    @Update("update user set pswd = #{pswd} where phone = #{phone}")
    void forgetPswd(@Param("phone")String phone, @Param("pswd") String pswd);

    @Update("update user set pswd = #{pswd} where idx = #{idx}")
    void alterPswd(@Param("idx")String idx, @Param("pswd") String pswd);

    @Update("update user set phone = #{phone} where idx = #{idx}")
    void alterPhone(@Param("idx") String idx,@Param("phone") String phone);

    @Update("update user set photo = #{photo} where idx = #{idx}")
    void alterPhoto(@Param("idx") String idx,@Param("photo") String photo);

    @Update("update user set descri = #{descri} where idx = #{idx}")
    void alterDescri(@Param("idx") String idx,@Param("descri") String descri);

    @Update("update user set bg = #{bg} where idx = #{idx}")
    void alterBg(@Param("idx") String idx,@Param("bg") String bg);

    @Update("update user set sex = #{sex},addr=#{addr} where idx = #{idx}") ////////////////////////
    void alterInfo(User user);

    @Update("update user set ltime = #{ltime} where idx = #{idx}")
    void updateLTime(String idx, String ltime);

    @Update("update user set score = #{score} where idx = #{idx}")
    void updateScore(String idx, String score);

    @Update("update user set qq = #{qq} where idx = #{idx}")
    void updateQQ(String idx, String qq);

    @Update("update user set status = #{status} where idx = #{idx}")
    void alterStatus(String idx, String status);

    @Select("select * user where idx = #{idx}")
    User getInfo(@Param("idx")String idx);

////////////////////////////////////////////////////////////////////////////////////////////////////////
    //以下为权限角色
    @Delete("delete from user_role where iduser = #{iduser}")
    int deleteUserRole(String iduser);

    int saveUserRoles(@Param("iduser") String iduser, @Param("idroles") List<String> roleIds);

    Integer count(@Param("params") Map<String, Object> params);

    List<User> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

}
