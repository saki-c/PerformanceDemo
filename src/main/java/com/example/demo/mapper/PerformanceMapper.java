package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Performance;


/**
 * @author 咲蛍
 * @date 2021/05/17
 */
public interface PerformanceMapper extends BaseMapper<Performance> {

    /**
     * 分页查询
     *
     * @param page    页码
     * @param keyword 关键字
     * @param userId  用户id
     * @return IPage
     */
    IPage<Performance> selectPerformancePage(Page<Performance> page, String keyword, String userId);

    /**
     * 分页查询
     *
     * @param page    页码
     * @param keyword 关键字
     * @param userId  用户id
     * @return IPage
     */
    IPage<Performance> selectOnePerformancePage(Page<Performance> page, String keyword, String userId);
}
