package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.service.LoginService;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * @author 咲蛍
 * @date 2021/05/17
 */
@RestController
public class LoginController {
    @Resource
     private LoginService loginService;

    @PostMapping(value = "/api/login")
    public Result<T> login(@Valid @RequestBody LoginDTO loginDTO){
 /*       if (bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((m1, m2) -> m1 +"；" + m2)
                    .orElse("参数输入有误！");
            return new Result<>(400,messages);
        }*/
        return loginService.login(loginDTO);
    }
}
