package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.PositionDTO;
import com.example.demo.dto.QueryDTO;
import com.example.demo.service.DepartmentService;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 咲蛍
 * @date 2021/6/28
 */
@RestController
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Resource
    private DepartmentService departmentService;

    private static final String TOKEN = "token";

    @PostMapping("api/department/list")
    public Result<T> departmentList(@RequestBody QueryDTO queryDTO) {
        logger.info("部门列表入参,{}", queryDTO);
        return departmentService.selectDepartmentPage(queryDTO);
    }

    @PostMapping("api/department/add")
    public Result<T> addDepartment(HttpServletRequest request, @Valid @RequestBody DepartmentDTO departmentDTO) {
        logger.info("添加部门入参,{}", departmentDTO);
        String token = request.getHeader(TOKEN);
        return departmentService.addDepartment(token, departmentDTO);
    }

    @PostMapping("api/department/position/list")
    public Result<T> positionList(Integer departmentId) {
        logger.info("部门岗位列表入参,{}", departmentId);
        return departmentService.selectPosition(departmentId);
    }

    @PostMapping("/api/department/delete")
    public Result<T> deleteDepartment(HttpServletRequest request, Integer departmentId) {
        logger.info("删除部门入参,{}", departmentId);
        String token = request.getHeader(TOKEN);
        return departmentService.deleteDepartment(token, departmentId);
    }

    @PostMapping("/api/department/position/add")
    public Result<T> addPosition(HttpServletRequest request, @Valid @RequestBody PositionDTO positionDTO) {
        logger.info("添加岗位入参,{}", positionDTO);
        String token = request.getHeader(TOKEN);
        return departmentService.addPosition(token, positionDTO);
    }

    @PostMapping("/api/department/position/delete")
    public Result<T> deletePosition(HttpServletRequest request, Integer positionId) {
        logger.info("删除岗位入参,{}", positionId);
        String token = request.getHeader(TOKEN);
        return departmentService.deletePosition(token, positionId);
    }

    @GetMapping("/api/department/position/list")
    public Result<T> positionList() {
        logger.info("岗位列表");
        return departmentService.positionList();
    }
}
