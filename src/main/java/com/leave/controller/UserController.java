package com.leave.controller;

import com.leave.annotation.LogAnnotation;
import com.leave.dto.UserDto;
import com.leave.mapper.UserImplMapper;
import com.leave.model.User;
import com.leave.service.UserService;
import com.leave.table.PageTableHandler;
import com.leave.table.PageTableRequest;
import com.leave.table.PageTableResponse;
import com.leave.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Api(tags = "用户信息接口")
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    UserService userService;
    @Autowired
    UserImplMapper userMapper;

    @LogAnnotation
    @PostMapping("register")
    @ApiOperation(value="用户注册",notes="电话注册")
    @ResponseBody
    @PreAuthorize("hasAuthority('user:add')")
    public String insertUser(@RequestBody @Valid @ApiParam(name="User",value="json格式") UserDto user) throws Exception {
        System.out.println(user.toString());
        if(userMapper.getInfoByPhone(user.getPhone()) != null)return "不能重复注册";
        user.setPhoto("http://47.104.191.228:8089/lev/default.png");
        return userService.register(user);
    }


    @PostMapping("login")
    @ApiOperation(value="用户登录",notes="电话登录")
    @ResponseBody
    public String loginPhone(@RequestBody @Valid @ApiParam(name="User",value="json格式") User user) throws Exception {
        System.out.println(user.toString());
        return userService.loginPhone(user.getPhone(), user.getPswd());
    }

    @LogAnnotation
    @PostMapping("alter/1")//@PutMapping
    @ApiOperation(value = "修改用户")
    @PreAuthorize("hasAuthority('user:add')")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.alterInfo(userDto);
    }

    @LogAnnotation
    @PostMapping("alter/2")//@PutMapping(params = "headImgUrl")
    @ApiOperation(value = "修改头像")
    public void updateHeadImgUrl(String headImgUrl) {
        User user = UserUtil.getLoginUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setPhoto(headImgUrl);

        userService.alterPhoto(userDto);
        log.debug("{}修改了头像", user.getIdx());
    }

    @LogAnnotation
    @PostMapping("alter/3")//@PutMapping("/{username}")
    @ApiOperation(value = "修改密码")
    @PreAuthorize("hasAuthority('user:password')")
    public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
    }

    @GetMapping
    @ApiOperation(value = "用户列表")
    @PreAuthorize("hasAuthority('user:query')")
    public PageTableResponse listUsers(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return userMapper.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<User> list(PageTableRequest request) {
                List<User> list = userMapper.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/current")
    public User currentUser() {
        return UserUtil.getLoginUser();
    }

    @ApiOperation(value = "根据用户id获取用户")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:query')")
    public User user(@PathVariable String id) {
        return userMapper.getInfo(id);
    }
}
