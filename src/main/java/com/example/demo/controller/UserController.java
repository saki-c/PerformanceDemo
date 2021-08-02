package com.example.demo.controller;

import com.example.demo.dto.PassDTO;
import com.example.demo.dto.QueryDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;


/**
 * @author 咲蛍
 * @date 2021/05/18
 */

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    private static final String TOKEN = "token";

    /**
     * 添加用户
     *
     * @param userDTO userDTO
     * @return Result
     * @throws ParseException 编译异常
     */
    @PostMapping("/api/user/add")
    public Result<T> addUser(HttpServletRequest request, @Valid @RequestBody UserDTO userDTO) throws ParseException {
        logger.info("添加用户入参,{}", userDTO);
        String token = request.getHeader(TOKEN);
        return userService.addUser(token, userDTO);
    }

    /**
     * 分页查询
     *
     * @param queryDTO queryDTO
     * @return Result
     */
    @PostMapping("/api/user/list")
    public Result<T> userList(HttpServletRequest request, @RequestBody QueryDTO queryDTO) {
        logger.info("用户列表入参,{}", queryDTO);
        String token = request.getHeader(TOKEN);
        return userService.selectUserPage(token, queryDTO);
    }

    /**
     * 业务删除
     *
     * @param userId 用户id
     * @return Result
     */
    @PostMapping("/api/user/delete")
    public Result<T> deleteUser(HttpServletRequest request, Integer userId) {
        logger.info("用户删除入参,{}", userId);
        String token = request.getHeader(TOKEN);
        return userService.deleteUser(token, userId);
    }

    /**
     * 修改密码
     *
     * @param passDTO passDTO
     * @return Result
     */
    @PostMapping("/api/user/update")
    public Result<T> updateUser(HttpServletRequest request, @Valid @RequestBody PassDTO passDTO) {
        logger.info("修改密码入参,{}", passDTO);
        String token = request.getHeader(TOKEN);
        return userService.updateUser(token, passDTO);

    }

    /**
     * 更新
     *
     * @param userDTO passDTO
     * @return Result
     */
    @PostMapping("/api/user/revise")
    public Result<T> reviseUser(HttpServletRequest request, @RequestBody UserDTO userDTO) {
        logger.info("用户信息更新入参,{}", userDTO);
        String token = request.getHeader(TOKEN);
        return userService.reviseUser(token, userDTO);
    }

    /**
     * 重置密码
     *
     * @param userId 用户id
     * @return passDTO
     */
    @PostMapping("/api/user/reset")
    public Result<T> resetPassword(HttpServletRequest request, Integer userId) {
        logger.info("重置密码入参,{}", userId);
        String token = request.getHeader(TOKEN);
        return userService.resetPassword(token, userId);
    }

    /**
     * 提升管理员
     *
     * @param userId 用户id
     * @return Result
     */
    @PostMapping("/api/user/promote")
    public Result<T> promoteUser(HttpServletRequest request, Integer userId) {
        logger.info("提升管理员入参,{}", userId);
        String token = request.getHeader(TOKEN);
        return userService.promoteUser(token, userId);
    }

    /**
     * 降低为用户
     *
     * @param userId 用户id
     * @return Result
     */
    @PostMapping("/api/user/degrade")
    public Result<T> degradeUser(HttpServletRequest request, Integer userId) {
        logger.info("降低为用户入参,{}", userId);
        String token = request.getHeader(TOKEN);
        return userService.degradeUser(token, userId);
    }

    /**
     * 部门管理员列表
     *
     * @return Result
     */
    @GetMapping("/api/user/admin")
    public Result<T> adminList(HttpServletRequest request) {
        logger.info("部门管理员列表");
        String token = request.getHeader(TOKEN);
        return userService.adminList(token);
    }

    /**
     * 获取登陆账号信息
     *
     * @param request
     * @return
     */
    @GetMapping("/api/user/account")
    public Result<T> selectUser(HttpServletRequest request) {
        logger.info("登陆账号信息");
        String token = request.getHeader(TOKEN);
        return userService.selectUser(token);
    }

    /**
     * 管理员列表
     *
     * @return Result
     */
    @GetMapping("/api/user/alladmin")
    public Result<T> allAdminList() {
        logger.info("管理员列表");
        return userService.allAdminList();
    }
}
