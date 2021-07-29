package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.PerformanceService;
import com.example.demo.util.Result;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author 咲蛍
 * @date 2021/05/17
 */
@RestController
public class PerformanceController {
    @Resource
    private PerformanceService performanceService;

    private static final String TOKEN = "token";

    /**
     * 添加绩效
     * @param performanceDTO performanceDTO
     * @return Result
     * @throws ParseException 编译异常
     */
    @PostMapping("/api/performance/add")
    public Result<T> performanceAdd(HttpServletRequest request, @Valid @RequestBody PerformanceDTO performanceDTO) throws ParseException {
        String token = request.getHeader(TOKEN);
        return performanceService.addPerformance(token, performanceDTO);
    }

    /**
     * 分页查询
     * @param queryDTO queryDTO
     * @return Result
     */
    @PostMapping("/api/performance/list")
    public Result<T> performanceList(HttpServletRequest request, @RequestBody QueryDTO queryDTO) {
        String token = request.getHeader(TOKEN);
        return performanceService.selectPerformancePage(token, queryDTO);
    }

    /**
     * excel下载
     * @param downloadDTO downloadDTO
     * @throws IOException IO
     */
    @PostMapping(value = "/api/performance/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void performanceDownload(HttpServletResponse response, @RequestBody DownloadDTO downloadDTO) throws IOException {
        performanceService.downloadPerformance(response, downloadDTO);
    }

    /**
     * 单项查询
     * @param performanceId 绩效id
     * @return Result
     */
    @PostMapping("/api/performance/item/list")
    public Result<T> performanceItemList(Integer performanceId) {
        return performanceService.selectPerformanceItemPage(performanceId);
    }

    /**
     * 修改绩效条目
     * @param request 头token
     * @param itemDTO itemDTO
     * @return Result
     */
    @PostMapping("/api/performance/update")
    public Result<T> performanceItemEdit(HttpServletRequest request, @RequestBody ItemDTO itemDTO) {
        String token = request.getHeader(TOKEN);
        return performanceService.updatePerformanceItem(token, itemDTO);
    }

    /**
     * 提交绩效
     * @param request 头token
     * @param performanceId 绩效id
     * @return Result
     */
    @PostMapping("/api/performance/submit")
    public Result<T> performanceSubmit(HttpServletRequest request,Integer performanceId){
        String token = request.getHeader(TOKEN);
        return performanceService.submitPerformance(token,performanceId);
    }

    /**
     * 保存评分
     * @param request 头token
     * @param performanceId 绩效id
     * @return Result
     */
    @PostMapping("/api/performance/grade")
    public Result<T> performanceGrade(HttpServletRequest request,Integer performanceId){
        String token = request.getHeader(TOKEN);
        return performanceService.gradePerformance(token,performanceId);
    }

    /**
     * 总结
     * @param request 头token
     * @param summaryDTO summaryDTO
     * @return Result
     */
    @PostMapping("/api/performance/summary")
    public  Result<T> summaryAdd(HttpServletRequest request, @RequestBody SummaryDTO summaryDTO){
        String token = request.getHeader(TOKEN);
        return performanceService.addSummary(token,summaryDTO);
    }
}
