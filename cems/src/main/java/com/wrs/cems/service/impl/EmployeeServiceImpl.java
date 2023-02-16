package com.wrs.cems.service.impl;

import com.github.pagehelper.Page;
import com.wrs.cems.bean.Employee;
import com.wrs.cems.mapper.EmployeeMapper;
import com.wrs.cems.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmpById(Integer EId) {
        return employeeMapper.getEmpById(EId);
    }

    @Override
    public Employee getEmpAndDeptById(Integer EId) {
        return employeeMapper.getEmpAndDeptById(EId);
    }

    @Override
    public Employee getEmpByWorkID(String workID) {
        return employeeMapper.getEmpByWorkID(workID);
    }

    @Override
    public List<Employee> getAllEmps() {
        return employeeMapper.getAllEmps();
    }

    @Override
    public List<Employee> getEmpAndDept() {
        return employeeMapper.getEmpAndDept();
    }

    @Override
    public Integer deleteEmpById(Integer EId) {
        return employeeMapper.deleteEmpById(EId);
    }

    @Override
    public Integer delecteEmpByWorkID(String workID) {
        return employeeMapper.deleteEmpByWorkID(workID);
    }

    @Override
    public Integer addEmp(Employee emp) {
        return employeeMapper.addEmp(emp);
    }

    @Override
    public Integer updateEmp(Employee emp) {
        return employeeMapper.updateEmp(emp);
    }

    @Override
    public List<Employee> query(String name,String workID,String deptname,String workDate)
    {
        if (workDate != null && !workDate.equals("") && workDate.length() < 10) {
            workDate = "%" + workDate + "%";
        }
        return employeeMapper.query(name,workID,deptname,workDate);
    }

}
