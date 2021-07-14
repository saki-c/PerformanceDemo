package com.example.demo.controller;

import com.example.demo.dto.PassDTO;
import com.example.demo.dto.QueryDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;


/**
 * @author 涼月
 * @date 2021/05/18
 */

@RestController
public class UserController {
    @Resource
     private UserService userService;

    /**
     * 添加用户
     * @param userDTO userDTO
     * @return Result
     * @throws ParseException 编译异常
     */
    @PostMapping("/api/user/add")
    public Result addUser(HttpServletRequest request, @RequestBody UserDTO userDTO) throws ParseException {
        String token = request.getHeader("token");
        return userService.addUser(token,userDTO);
    }

    /**
     * 分页查询
     *
     * @param queryDTO queryDTO
     * @return Result
     */
    @PostMapping("/api/user/list")
    public Result userList(HttpServletRequest request, @RequestBody QueryDTO queryDTO) {
        String token = request.getHeader("token");
        return userService.selectUserPage(token,queryDTO);
    }

    /**
     * 业务删除
     *
     * @param userId 用户id
     * @return Result
     */
    @PostMapping("/api/user/delete")
    public Result deleteUser(HttpServletRequest request, Integer userId) {
        String token = request.getHeader("token");
        return userService.deleteUser(token,userId);
    }

    /**
     * 更新
     *
     * @param passDTO passDTO
     * @return Result
     */
    @PostMapping("/api/user/update")
    public Result updateUser(HttpServletRequest request, @RequestBody PassDTO passDTO) {
        String token = request.getHeader("token");
        return userService.updateUser(token,passDTO);

    }

    /**
     * 重置密码
     *
     * @param userId 用户id
     * @return passDTO
     */
    @PostMapping("/api/user/reset")
    public Result resetPassword(HttpServletRequest request, Integer userId) {
        String token = request.getHeader("token");
        return userService.resetPassword(token,userId);
    }

    /**
     * 提升管理员
     * @param userId 用户id
     * @return Result
     */
    @PostMapping("/api/user/promote")
    public Result promoteUser(HttpServletRequest request, Integer userId) {
        String token = request.getHeader("token");
        return userService.promoteUser(token,userId);
    }

    /**
     * 降低为用户
     * @param userId 用户id
     * @return Result
     */
    @PostMapping("/api/user/degrade")
    public Result degradeUser(HttpServletRequest request, Integer userId) {
        String token = request.getHeader("token");
        return userService.degradeUser(token,userId);
    }

    /**
     * 部门管理员列表
     * @return Result
     */
    @GetMapping("/api/user/admin")
    public Result adminList(HttpServletRequest request){
        String token = request.getHeader("token");
        return userService.adminList(token);
    }

    /**
     * 管理员列表
     * @return Result
     */
    @GetMapping("/api/user/alladmin")
    public Result allAdminList(){
        return userService.allAdminList();
    }
}
