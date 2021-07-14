package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Position;

/**
 * @author 涼月
 * @date 2021/6/26
 */
public interface PositionMapper extends BaseMapper<Position> {
    /**
     * 查询
     * @param positionName 岗位名称
     * @return Position
     */
    Position selectByPositionName(String positionName);
}
