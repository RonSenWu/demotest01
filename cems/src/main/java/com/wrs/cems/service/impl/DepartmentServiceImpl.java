package com.wrs.cems.service.impl;


import com.wrs.cems.bean.Department;
import com.wrs.cems.mapper.DepartmentMapper;
import com.wrs.cems.service.IDeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDeparmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department getDeptById(Integer id) {
        return departmentMapper.getDeptById(id);
    }

    @Override
    public Department getDeptByName(String name) {
        return departmentMapper.findDeptByName(name);
    }

    @Override
    public Department getAllName() {
        return departmentMapper.getAllName();
    }

    @Override
    public List<Department> getAllDepts() {
        return departmentMapper.getAllDepts();
    }

}
