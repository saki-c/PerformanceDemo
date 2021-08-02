package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.service.LoginService;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;

    @PostMapping(value = "/api/login")
    public Result<T> login(@Valid @RequestBody LoginDTO loginDTO) {
        logger.info("登陆入参,{}", loginDTO);
        return loginService.login(loginDTO);
    }
}
