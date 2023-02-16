package com.wrs.cems.mapper;



import com.wrs.cems.bean.Empsalary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpsalaryMapper {
    //    根据id查询员工薪水
    Empsalary getEmpsalaryById(Integer SId);

    //    查询所有员工薪水
    List<Empsalary> getAllEmpsalarys();

    List<Empsalary> getAll();

    //    根据id删除员工薪水
    int deleteEmpsalaryById(Integer SId);

    //    添加员工薪水
    int addEmpsalary(Empsalary empsalary);

    //    修改薪水
    int updateEmpsalary(Empsalary empsalary);

    List<Empsalary> query(String name,String SWorkId,String salDate);
}
