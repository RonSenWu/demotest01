package com.wrs.cems.mapper;


import com.wrs.cems.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    //    根据id查询员工
    Employee getEmpById(Integer EId);

    Employee getEmpAndDeptById(Integer EId);

    //    根据workID查询员工
    Employee getEmpByWorkID(String workID);

    //    查询所有员工
    List<Employee> getAllEmps();

    List<Employee> getEmpAndDept();

    //    根据id删除员工
    Integer deleteEmpById(Integer EId);

    //    根据workID删除员工
    Integer deleteEmpByWorkID(String workID);

    //    添加员工
    Integer addEmp(Employee emp);

    //    修改员工信息
    Integer updateEmp(Employee emp);

    List<Employee> query(String name,String workID,String deptname,String workDate);

   /* List<Employee> inquiry(@Param("name") String name,
                           @Param("workID")String workID,
                           @Param("deptname")String deptname,
                           @Param("workDate")String workDate);*/
}
