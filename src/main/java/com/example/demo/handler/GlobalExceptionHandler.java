package com.example.demo.handler;

import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 咲蛍
 * @date 2021/7/29
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public Result<T> handlerEx(BindException e) {
        String messages = e.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .reduce((m1, m2) -> m1 + "；" + m2)
                .orElse("参数输入有误！");
        return new Result<>(400, messages);
    }
}