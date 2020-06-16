package com.leave.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.leave.utils.HttpUtils;
import com.leave.utils.SmsUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {
    static final Logger LOGGER = LoggerFactory.getLogger(SmsUtils.class);

    //1、云市场、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
    final String host = "https://feginesms.market.alicloudapi.com";
    final String path = "/codeNotice";
    final String method = "GET";
    final String appcode = "a3b7c145592f4464a5c8fdfe74ba5d0b";

    public void sms(String phone,String code) {  //ok
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("param", code);
        querys.put("phone", phone);
        querys.put("sign", "1");
        querys.put("skin", "1");
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            String ans = EntityUtils.toString(response.getEntity());
            //System.out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2、阿里云、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
    @Autowired
    private SmsUtils sendUtils;

    //发送验证码
    public CommonResponse send(String phone, String code) throws ClientException {
        CommonResponse response = sendUtils.send(phone,code);
        if (response != null && response.getHttpStatus() == 200){
            String data = response.getData();
            response.setData("code:"+code+",date:"+data);
            Map map = JSON.parseObject(data, Map.class);
            LOGGER.info("短信发送状态:{}" + phone + "=====" + code + "=====" + map.get("Message"));
        }
        //以下为Api的测试代码，不做理会即可
//        boolean success = response.getHttpResponse().isSuccess();  //true
//        int status = response.getHttpResponse().getStatus(); //200
//        int httpStatus = response.getHttpStatus(); //200
        return response;
    }

}
