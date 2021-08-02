package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Department;
import com.example.demo.entity.User;
import com.example.demo.vo.DepartmentVO;

/**
 * @author 咲蛍
 * @date 2021/6/26
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * 分页查询
     *
     * @param page    页码
     * @param keyword 关键字
     * @return IPage
     */
    IPage<User> selectDepartmentPage(Page<DepartmentVO> page, String keyword);

    /**
     * 查询
     *
     * @param departmentName 部门名称
     * @return Department
     */
    Department selectByDepartmentName(String departmentName);
}
