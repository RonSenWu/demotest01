package com.wrs.cems.mapper;



import com.wrs.cems.bean.Nation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationMapper {
    List<Nation> getAllNations();
}
