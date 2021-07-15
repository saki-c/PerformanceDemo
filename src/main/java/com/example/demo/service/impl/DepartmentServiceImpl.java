package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.PositionDTO;
import com.example.demo.dto.QueryDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Position;
import com.example.demo.entity.User;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.mapper.PositionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.DepartmentService;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.Result;
import com.example.demo.vo.DepartmentVO;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 涼月
 * @date 2021/6/28
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PositionMapper positionMapper;

    @Override
    public Result selectDepartmentPage(QueryDTO queryDTO) {
        Page<DepartmentVO> page = new Page<>(queryDTO.getPageNo(), queryDTO.getPageSize());
        return new Result<>(200, "", departmentMapper.selectDepartmentPage(page, queryDTO.getKeyword()));
    }

    @Override
    public Result addDepartment(String token, DepartmentDTO departmentDTO) {
        if (StringUtils.isEmpty(departmentDTO.getName())) {
            return new Result(400, "部门名称不能为空");
        }
        if (null == departmentDTO.getManagerId()) {
            return new Result(400, "部门主管不能为空");
        }
        if (isSuperAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result(400, "权限不足");
        }
        if (null != departmentMapper.selectByDepartmentName(departmentDTO.getName())) {
            return new Result(400, "已存在此部门");
        }

        User user = userMapper.selectById(departmentDTO.getManagerId());
        if (null == user) {
            return new Result(400, "不存在此用户");
        }
        if (!"admin".equals(user.getUserStatus())) {
            return new Result(400, "此用户权限不足");
        }

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department_manager_id", departmentDTO.getManagerId());
        queryWrapper.eq("department_status", "exist");
        Department department = departmentMapper.selectOne(queryWrapper);
        if (null != department) {
            return new Result(400, "此人已经是其他部门主管了");
        }

        Department department1 = new Department();
        department1.setDepartmentName(departmentDTO.getName());
        department1.setDepartmentManagerId(departmentDTO.getManagerId());
        departmentMapper.insert(department1);
        int departmentId = department1.getDepartmentId();

        Position position = new Position();
        position.setPositionDepartmentId(departmentId);
        position.setPositionName("部门主管");
        position.setPositionStatus("admin");
        positionMapper.insert(position);
        int positionId = position.getPositionId();

        user.setUserPositionId(positionId);
        userMapper.updateById(user);

        return new Result(200, "添加成功");
    }

    @Override
    public Result selectPosition(Integer departmentId) {
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("position_department_id", departmentId);
        queryWrapper.eq("position_status", "exist");
        List<Position> list = positionMapper.selectList(queryWrapper);
        return new Result<>(200, "", list);
    }

    @Override
    public Result deleteDepartment(String token, Integer departmentId) {
        if (isSuperAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result(400, "权限不足");
        }
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentStatus("delete");
        departmentMapper.updateById(department);
        return new Result(200, "删除成功");
    }

    @Override
    public Result addPosition(String token, PositionDTO positionDTO) {
        if (StringUtils.isEmpty(positionDTO.getName())) {
            return new Result(400, "岗位名称不能为空");
        }
        if (null == positionDTO.getDepartmentId()) {
            return new Result(400, "所属部门不能为空");
        }
        if (isAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result(400, "权限不足");
        }
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("position_name", positionDTO.getName());
        queryWrapper.eq("position_department_id", positionDTO.getDepartmentId());
        queryWrapper.eq("position_status", "exist");
        if (null != positionMapper.selectOne(queryWrapper)) {
            return new Result(400, "该部门已存在此岗位");
        }
        Position position = new Position();
        position.setPositionName(positionDTO.getName());
        position.setPositionDepartmentId(positionDTO.getDepartmentId());
        positionMapper.insert(position);
        return new Result<>(200, "添加成功", position);
    }

    @Override
    public Result deletePosition(String token, Integer positionId) {
        if (isAdmin(JWTUtil.verifyToken(token).get("id").asString())) {
            return new Result(400, "权限不足");
        }
        Position position = new Position();
        position.setPositionId(positionId);
        position.setPositionStatus("delete");
        positionMapper.updateById(position);
        return new Result(200, "删除成功");
    }

    @Override
    public Result positionList() {
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("position_status", "exist");
        List<Position> list = positionMapper.selectList(queryWrapper);
        return new Result<>(200, "", list);
    }

    private boolean isAdmin(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        User user = userMapper.selectOne(wrapper);
        return "user".equals(user.getUserStatus());
    }

    private boolean isSuperAdmin(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        User user = userMapper.selectOne(wrapper);
        return !"superadmin".equals(user.getUserStatus());
    }
}
