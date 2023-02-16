package com.wrs.cems.mapper;



import com.wrs.cems.bean.Emptrain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmptrainMapper {
    Emptrain getEmptrainById(Integer id);

    //    查询所有
    List<Emptrain> getAllEmptrains();

    List<Emptrain> getEmptrainAndEmp();

    //    根据id删除
    int deleteEmptrainById(Integer TId);

    //    添加
    int addEmptrain(Emptrain emptrain);

    //    更新
    int updateEmptrain(Emptrain emptrain);

    List<Emptrain> query(String name,String TWorkId,String deptname);
}
