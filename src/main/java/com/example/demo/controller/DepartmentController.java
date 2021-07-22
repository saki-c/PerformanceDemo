package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.PositionDTO;
import com.example.demo.dto.QueryDTO;
import com.example.demo.service.DepartmentService;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 咲蛍
 * @date 2021/6/28
 */
@RestController
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    String t = "token";

    @PostMapping("api/department/list")
    public Result<T> departmentList(@RequestBody QueryDTO queryDTO) {
        return departmentService.selectDepartmentPage(queryDTO);
    }

    @PostMapping("api/department/add")
    public Result<T> addDepartment(HttpServletRequest request, @RequestBody DepartmentDTO departmentDTO) {
        String token = request.getHeader(t);
        return departmentService.addDepartment(token, departmentDTO);
    }

    @PostMapping("api/department/position/list")
    public Result<T> positionList(Integer departmentId) {
        return departmentService.selectPosition(departmentId);
    }

    @PostMapping("/api/department/delete")
    public Result<T> deleteDepartment(HttpServletRequest request, Integer departmentId) {
        String token = request.getHeader(t);
        return departmentService.deleteDepartment(token, departmentId);
    }

    @PostMapping("/api/department/position/add")
    public Result<T> addPosition(HttpServletRequest request, @RequestBody PositionDTO positionDTO) {
        String token = request.getHeader(t);
        return departmentService.addPosition(token,positionDTO);
    }

    @PostMapping("/api/department/position/delete")
    public Result<T> deletePosition(HttpServletRequest request, Integer positionId){
        String token = request.getHeader(t);
        return departmentService.deletePosition(token, positionId);
    }

    @GetMapping("/api/department/position/list")
    public Result<T> positionList(){
        return departmentService.positionList();
    }
}
