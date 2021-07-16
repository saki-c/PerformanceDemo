package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.vo.UserVO;

import java.util.List;

/**
 *
 * @author 咲蛍
 * @date 2021/05/17
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     *昵称查询
     * @param userNickname 用户昵称
     * @return user
     */
    User selectByUserNickname(String userNickname);

    /**
     *用户分页查询
     * @param departmentId 部门id
     * @param page 分页
     * @param keyword 关键字
     * @return IPage
     */
    IPage<UserVO> selectUserPage(int departmentId, Page<UserVO> page, String keyword);

    /**
     * 管理员分页查询
     * @param page 分页
     * @param keyword 关键字
     * @return IPage
     */
    IPage<UserVO> selectAdminPage(Page<UserVO> page, String keyword);

    /**
     * 管理员查询
     * @param departmentId 部门id
     * @return 用户列表
     */
    List<User> selectAdminList(int departmentId);

    /**
     * 管理员列表
     * @return 用户列表
     */
    List<User> selectAllAdminList();

    /**
     * 查询用户
     * @param id
     * @return
     */
    UserVO selectUser(String id);
}
