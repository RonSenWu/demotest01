package com.wrs.cems.mapper;


import com.wrs.cems.bean.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PositionMapper {
    List<Position> getAllPositions();
    Position findPosById(Integer id);
    List<Position> findPosByDid(@Param("did") Integer did);
}
