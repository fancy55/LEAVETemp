package com.leave.exception;

import org.apache.ibatis.binding.BindingException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//处理全局异常
@ControllerAdvice //开启全局异常
public class GlobalExceptionHandle {
    //空指针异常
    @ExceptionHandler(value=NullPointerException.class)
    @ResponseBody
    public ResultBody NullPointerException(HttpServletRequest req, NullPointerException e){
        e.printStackTrace();
        return ResultBody.fail(ResultEnum.NULL, e.getMessage());
    }

    //Valid参数校验异常
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultBody BindingException(HttpServletRequest req, MethodArgumentNotValidException e){
        e.printStackTrace();
        return ResultBody.fail("参数校验异常");
    }
}
