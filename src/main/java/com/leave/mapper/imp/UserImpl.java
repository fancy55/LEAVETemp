package com.leave.mapper.imp;

import com.leave.model.User;

import java.util.List;
import java.util.Map;

public interface UserImpl {
    void register(String phone, String idx, String pswd); //电话注册(包括默认：头像、账号状态1、积分0)，不能重复注册，注销时账号还在

    String login(String phone, String pswd);//电话登录,返回idx
    void loginQQ(String qq, String pswd);//qq登录
    void loginWX(String wx, String pswd);//wx登录
    void loginMail(String mail, String pswd);//mail登录
    void loginWeibo(String weiBo, String pswd);//weiBo登录
    void loginGithub(String github, String pswd);//github登录

    void forgetPswd(String phone, String pswd);//忘记密码

    void alterPswd(String idx, String pswd);//修改密码
    void alterDescri(String idx, String descri);//修改个性签名
    void alterPhone(String idx, String phone);//修改电话
    void alterPhoto(String idx, String photo);//修改头像
    void alterBg(String idx, String bg);//修改背景
    void alterInfo(User user);//修改除头像、密码外其他信息

    void updateLTime(String idx, String ltime);//修改最后一次操作时间--系统检测
    void updateScore(String idx, String score);//修改积分
    void updateQQ(String idx, String qq);//更新QQ账号

    void alterStatus(String idx, String status);//修改状态---审核员

    User getInfo(String idx); //获取个人信息
}
