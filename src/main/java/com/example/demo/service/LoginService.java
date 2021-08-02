package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author 咲蛍
 * @date 2021/05/26
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param loginDTO loginDTO
     * @return Result
     */
    Result<T> login(LoginDTO loginDTO);
}
