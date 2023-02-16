package com.wrs.cems.service;



import com.wrs.cems.bean.Department;

import java.util.List;

public interface IDeparmentService {

    Department getDeptById(Integer id);

    Department getDeptByName(String name);

    Department getAllName();

    List<Department> getAllDepts();

}
