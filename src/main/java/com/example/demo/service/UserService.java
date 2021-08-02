package com.example.demo.service;

import com.example.demo.dto.PassDTO;
import com.example.demo.dto.QueryDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;

import java.text.ParseException;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
public interface UserService {
    /**
     * 分页查询
     *
     * @param token    token
     * @param queryDTO queryDTO
     * @return Result
     */
    Result<T> selectUserPage(String token, QueryDTO queryDTO);

    /**
     * 添加用户
     *
     * @param userDTO userDTO
     * @param token   token
     * @return Result
     * @throws ParseException 编译异常
     */
    Result<T> addUser(String token, UserDTO userDTO) throws ParseException;

    /**
     * 业务删除
     *
     * @param userId 用户id
     * @param token  token
     * @return Result
     */
    Result<T> deleteUser(String token, int userId);

    /**
     * 重置密码
     *
     * @param token   token
     * @param passDTO passDTO
     * @return Result
     */
    Result<T> updateUser(String token, PassDTO passDTO);

    /**
     * 修改用户信息
     *
     * @param token   token
     * @param userDTO userDTO
     * @return Result
     */
    Result<T> reviseUser(String token, UserDTO userDTO);

    /**
     * 重置密码
     *
     * @param token  token
     * @param userId 用户id
     * @return Result
     */
    Result<T> resetPassword(String token, Integer userId);

    /**
     * 提升管理员
     *
     * @param token  token
     * @param userId 用户id
     * @return Result
     */
    Result<T> promoteUser(String token, Integer userId);

    /**
     * 降低为用户
     *
     * @param token  token
     * @param userId 用户id
     * @return Result
     */
    Result<T> degradeUser(String token, Integer userId);

    /**
     * 部门管理员列表
     *
     * @param token token
     * @return Result
     */
    Result<T> adminList(String token);

    /**
     * 管理员列表
     *
     * @return Result
     */
    Result<T> allAdminList();

    /**
     * 获取登陆账号信息
     *
     * @param token token
     * @return Result
     */
    Result<T> selectUser(String token);
}
