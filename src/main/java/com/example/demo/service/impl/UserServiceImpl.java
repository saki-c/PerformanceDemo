package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.PassDTO;
import com.example.demo.dto.QueryDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Position;
import com.example.demo.entity.User;
import com.example.demo.mapper.PositionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.MD5Util;
import com.example.demo.util.Result;
import com.example.demo.vo.UserVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    PositionMapper positionMapper;

    private static final String UTF = "utf-8";

    private static final String MESS = "权限不足";

    @Override
    public Result selectUserPage(String token, QueryDTO queryDTO) {
        Page<UserVO> page = new Page<>(queryDTO.getPageNo(), queryDTO.getPageSize());
        try {
            int positionId = Integer.parseInt(JWTUtil.verifyToken(token).get("position").asString());
            Position position = positionMapper.selectById(positionId);
            return new Result<>(200, "", userMapper.selectUserPage(position.getPositionDepartmentId(), page, queryDTO.getKeyword()));
        } catch (NumberFormatException e) {
            return new Result<>(200, "", userMapper.selectAdminPage(page, queryDTO.getKeyword()));
        }

    }

    @Override
    public Result addUser(String token, UserDTO userDTO) throws ParseException {
        if (userMapper.selectByUserNickname(userDTO.getNickname()) != null) {
            return new Result<T>(400, "已存在此用户");
        }
        if (isAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result<T>(400, MESS);
        }
        User user = new User();
        user.setUserName(userDTO.getName());
        user.setUserNickname(userDTO.getNickname());
        user.setUserPositionId(userDTO.getPositionId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(userDTO.getJoinDate());
        user.setUserJoinDate(date);

        user.setUserDirectorId(userDTO.getDirectorId());
        user.setUserPassword(MD5Util.MD5Encode(userDTO.getPassword(), UTF));
        user.setUserStatus("user");
        userMapper.insert(user);

        return new Result<>(200, "添加成功", user);
    }

    @Override
    public Result deleteUser(String token, int userId) {
        if (isAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result<T>(400, MESS);
        }
        User user = new User();
        user.setUserId(userId);
        user.setUserStatus("delete");
        userMapper.updateById(user);
        return new Result<T>(200, "删除成功");
    }

    @Override
    public Result updateUser(String token, PassDTO passDTO) {
        if (passDTO.getOldPassword().equals(passDTO.getNewPassword())) {
            return new Result<T>(400, "新密码与原密码一致");
        }
        if (!passDTO.getNewPassword().equals(passDTO.getAgainPassword())) {
            return new Result<T>(400, "两次密码不一致");
        }

        User user = userMapper.selectById(JWTUtil.verifyToken(token).get("id").asString());
        if (!user.getUserPassword().equals(MD5Util.MD5Encode(passDTO.getOldPassword(), UTF))) {
            return new Result<T>(400, "原密码错误");
        }
        user.setUserPassword(MD5Util.MD5Encode(passDTO.getNewPassword(), UTF));
        userMapper.updateById(user);
        return new Result<T>(401, "密码修改成功,请重新登陆");
    }

    @Override
    public Result reviseUser(String token, UserDTO userDTO) {
        User user = userMapper.selectById(JWTUtil.verifyToken(token).get("id").asString());
        if (null != userDTO.getNickname() && !"".equals(userDTO.getNickname())) {
            user.setUserNickname(userDTO.getNickname());
        }
        if (null != userDTO.getDirectorId()) {
            user.setUserDirectorId(userDTO.getDirectorId());
        }
        if (null != userDTO.getPositionId()) {
            user.setUserPositionId(userDTO.getPositionId());
        }
        userMapper.updateById(user);
        return new Result<T>(200, "成功");
    }

    @Override
    public Result resetPassword(String token, Integer userId) {
        if (isAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result<T>(400, MESS);
        }
        User user = new User();
        user.setUserPassword(MD5Util.MD5Encode("zhimarthome", UTF));
        user.setUserId(userId);
        userMapper.updateById(user);
        return new Result<T>(200, "重置成功");
    }

    @Override
    public Result promoteUser(String token, Integer userId) {
        if (isAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result<T>(400, MESS);
        }
        User user = new User();
        user.setUserId(userId);
        user.setUserStatus("admin");
        userMapper.updateById(user);
        return new Result<T>(200, "提升成功");
    }

    @Override
    public Result degradeUser(String token, Integer userId) {
        if (isAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result<T>(400, MESS);
        }
        User user = new User();
        user.setUserId(userId);
        user.setUserStatus("user");
        userMapper.updateById(user);
        return new Result<>(200, "降低成功");
    }

    @Override
    public Result adminList(String token) {
        try {
            Position position = positionMapper.selectById(Integer.parseInt(JWTUtil.verifyToken(token).get("position").asString()));
            return new Result<>(200, "", userMapper.selectAdminList(position.getPositionDepartmentId()));
        } catch (NumberFormatException e) {
            return new Result<T>(400, "不存在");
        }

    }

    @Override
    public Result allAdminList() {
        return new Result<>(200, "", userMapper.selectAllAdminList());
    }

    @Override
    public Result selectUser(String token) {
        return new Result<>(200, "", userMapper.selectUser(JWTUtil.verifyToken(token).get("id").asString()));
    }

    private boolean isAdmin(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        User user = userMapper.selectOne(wrapper);
        return "user".equals(user.getUserStatus());
    }
}

