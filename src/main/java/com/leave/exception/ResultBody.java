package com.leave.exception;

import lombok.Builder;
import lombok.Data;

//响应数据
@Data
@Builder
public class ResultBody {
    private String code;
    private String msg;
    private String res;

    ResultBody fail(String code, String msg){
        return ResultBody.builder()
                .code(code).msg(msg).res(null)
                .build();
    }

    static ResultBody fail(String res){
        return ResultBody.builder()
                .code(null).msg(null).res(res)
                .build();
    }

    static ResultBody fail(ResultEnum e, String msg){
        return ResultBody.builder()
                .code(e.getCode()).msg(e.getMsg()).res(msg)
                .build();
    }
}
