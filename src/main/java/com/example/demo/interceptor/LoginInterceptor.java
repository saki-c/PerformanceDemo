package com.example.demo.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.util.HttpContextUtil;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        //从header中获取token
        String token = request.getHeader("token");
        //如果token为空
        if (StringUtils.isBlank(token)) {
            setReturn(response, 401, "用户未登录，请先登录");
            return false;
        }
        if (!JWTUtil.verify(token)) {
            setReturn(response, 401, "用户信息错误，请重新登录");
            return false;
        }
        if (JWTUtil.isExpired(token)) {
            setReturn(response, 401, "登录状态错误，请重新登录");
            return false;
        }

        return true;
    }

    /**
     * @param response response
     * @param code     code
     * @param msg      msg
     * @throws IOException IO异常
     */
    private static void setReturn(HttpServletResponse response, Integer code, String msg) throws IOException {
        HttpServletResponse httpResponse = response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        //UTF-8编码
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        Result<T> result = new Result<>(code, msg);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        httpResponse.getWriter().print(json);
    }

}