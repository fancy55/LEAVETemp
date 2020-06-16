package com.leave.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("register")
    @ApiOperation(value="用户注册页面",notes="电话注册")
    public String register() {
        return "register";
    }

    @GetMapping("login")
    @ApiOperation(value="用户登录页面",notes="电话登录")
    public String loginQQ() {
        return "login";
    }

    @GetMapping("err")
    @ApiOperation(value="异常",notes="异常")
    public String err() {
        return "err";
    }

    @GetMapping("/")
    @ApiOperation(value="用户登录页面",notes="电话登录")
    public String login() {
        return "login";
    }
}
