package com.wrs.cems.mapper;

import com.wrs.cems.bean.Assessment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssessmentMapper {
    Assessment getAssessmentById(Integer id);

    //    查询所有

    List<Assessment> getAll();

    //    根据id删除
    int deleteAssessmentById(Integer id);

    //    添加
    int addAssessment(Assessment assessment);

    //    更新
    int updateAssessment(Assessment assessment);

    List<Assessment> query(String name);
}
