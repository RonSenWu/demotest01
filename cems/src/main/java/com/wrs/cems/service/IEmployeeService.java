package com.wrs.cems.service;

import com.wrs.cems.bean.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee getEmpById(Integer EId);

    Employee getEmpAndDeptById(Integer EId);

    Employee getEmpByWorkID(String workID);

    List<Employee> getAllEmps();

    List<Employee> getEmpAndDept();

    Integer deleteEmpById(Integer EId);

    Integer delecteEmpByWorkID(String workID);

    Integer addEmp(Employee emp);

    Integer updateEmp(Employee emp);

    List<Employee> query(String name,String workID,String deptname,String workDate);

    /*List<Employee> inquiry(
                    String name,
                    String workID,
                    String deptname,
                    String workDate);*/
}
