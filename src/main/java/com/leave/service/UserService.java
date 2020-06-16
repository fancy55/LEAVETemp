package com.leave.service;

import com.leave.dto.UserDto;
import com.leave.mapper.UserDao;
import com.leave.model.User;
import com.leave.utils.RsaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    UserDao userMapper;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public String register(UserDto userDto) throws Exception {
        String phone = userDto.getPhone();
        String pswd = userDto.getPswd();
        String str = phone.substring(3,7).concat(pswd.substring(4, 8));
        String idx = RsaUtil.enPass(str, 5, 15); //第一次生成就唯一
//        System.out.println("=========idx=========="+idx);
        saveUserRoles(idx, userDto.getRoleIds());
        userMapper.register(phone, idx, passwordEncoder.encode(pswd));
        log.debug("新增用户{}", phone);
        return "success";
    }

    public String loginPhone( String phone, String pswd) {
        return userMapper.login(phone,pswd);
    }

    //根据账号查询用户信息
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userMapper.getInfoByPhone(phone);
        if(user == null)return null;
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getPhone()).password(user.getPswd()).authorities("vip").build();
        return userDetails;
    }

    //获取用户的身份信息
    public User getUser(String idx){
        String id = null;
        //当前用户通过的认证身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();//用户身份
        if(principal == null)id="匿名";
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            id = userDetails.getUsername();
        }else id = principal.toString();
        System.out.println(idx+"{}登录时认证为："+id);
        User user = userMapper.getInfo(idx);
        return user;
    }

    //为用户添加权限
    private void saveUserRoles(String idx, List<String> roleIds) {
        if (roleIds != null) {
            userMapper.deleteUserRole(idx);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userMapper.saveUserRoles(idx, roleIds);
            }
        }
    }

    //更改密码
    public void changePassword(String idx, String oldPassword, String newPassword) {
        User u = userMapper.getInfo(idx);
        if (u == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        if (!passwordEncoder.matches(oldPassword, u.getPswd())) {
            throw new IllegalArgumentException("旧密码错误");
        }

        userMapper.alterPswd(idx, passwordEncoder.encode(newPassword));

        log.debug("修改{}的密码", u.getPhone());
    }

    //修改用户信息           ???????
    public void alterInfo(UserDto userDto){
        userMapper.alterInfo(userDto);
    }

    //修改用户头像           ???????
    public void alterPhoto(UserDto userDto){
        userMapper.alterPhoto(userDto.getIdx(),userDto.getPhoto());
    }
}
