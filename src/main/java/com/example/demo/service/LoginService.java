package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.util.Result;
/**
 *
 * @author 涼月
 * @date 2021/05/26
 */
public interface LoginService {
    /**
     * 登录
     * @param loginDTO loginDTO
     * @return Result
     */
    Result login(LoginDTO loginDTO);
}
