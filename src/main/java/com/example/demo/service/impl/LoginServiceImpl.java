package com.example.demo.service.impl;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LoginService;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.MD5Util;
import com.example.demo.util.Result;
import com.example.demo.vo.LoginVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 咲蛍
 * @date 2021/05/26
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;


    @Override
    public Result login(LoginDTO loginDTO) {
        //通过登录名查询用户
        User user = userMapper.selectByUserNickname(loginDTO.getNickname());
        String passwordMd5 = MD5Util.MD5Encode(loginDTO.getPassword(), "utf-8");
        if (null == user) {
            return new Result<T>(400, "账号不存在");
        } else if (!user.getUserPassword().equals(passwordMd5)) {
            return new Result<T>(400, "密码错误");
        } else if (!"delete".equals(user.getUserStatus())) {
            LoginVO loginVO = new LoginVO();
            loginVO.setToken(JWTUtil.createJWT(user));
            loginVO.setUser(user);
            return new Result<>(200, "", loginVO);
        }
        return new Result<T>(400, "登录失败");
    }
}
