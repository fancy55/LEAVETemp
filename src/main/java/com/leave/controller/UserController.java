package com.leave.controller;

import com.leave.model.User;
import com.leave.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@Api("用户信息接口")
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    @ApiOperation(value="用户注册",notes="电话注册")
    @ResponseBody
    public String insertUser(@RequestBody @Valid @ApiParam(name="User",value="json格式") User user) throws Exception {
        System.out.println(user.toString());
        return userService.register(user.getPhone(), user.getPswd());
    }

    @PostMapping("login")
    @ApiOperation(value="用户登录",notes="电话登录")
    @ResponseBody
    public String loginPhone(@RequestBody @Valid @ApiParam(name="User",value="json格式") User user) throws Exception {
        System.out.println(user.toString());
        return userService.loginPhone(user.getPhone(), user.getPswd());
    }
}
