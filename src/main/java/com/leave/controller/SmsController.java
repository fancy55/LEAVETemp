package com.leave.controller;

import com.aliyuncs.exceptions.ClientException;
import com.leave.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Api("手机号")
public class SmsController {
    @Autowired
    private SmsService smsService;

    /**
     * 云市场
     * @param number
     * @return
     */
    @GetMapping("send/{number}")
    @ApiOperation("发送验证码")
    public String cmsSend(@PathVariable String number){
        //随机产生6位验证码
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i<6; i++){
            int j = random.nextInt(10);
            code.append(j);
        }
        smsService.sms(number,code.toString());
        return code.toString();
    }

    /**
     * 阿里云
     * @param phone
     * @return
     * @throws ClientException
     */
    @GetMapping("send1/{phone}")
    @ResponseBody
    @ApiOperation("发送验证码")
    public String cmsSend1(@PathVariable String phone) throws ClientException {//@PathVariable

        //随机产生6位验证码
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i<6; i++){
            int j = random.nextInt(10);
            code.append(j);
        }
        smsService.send(phone,code.toString());
        return code.toString();
    }
}