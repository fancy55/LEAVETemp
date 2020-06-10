package com.leave.exception;

//定义错误码
public enum ResultEnum {
    SUCCESS("200","success"),
    NULL("400","null pointer"),
    NOT_FOUND("404", "not found"),
    PERMIT("401","not permission"),
    INTERNAL_SERVER_ERR("500", "服务器内部错误"),
    SERVER_BUSY("503","服务器正忙，请稍后再试");

    private String code;
    private String msg;

    ResultEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    String getCode(){
        return code;
    }

    String getMsg(){
        return msg;
    }
}
