package com.wrs.cems.service;

import com.wrs.cems.bean.Empsalary;

import java.util.List;

public interface IEmpsalaryService {
    Empsalary getEmpsalaryById(Integer id);

    List<Empsalary> getAllEmpsalarys();

    List<Empsalary> getAll();

    void deleteEmpsalaryById(Integer id);


    int addEmpsalary(Empsalary empsalary);

    void updateEmpsalary(Empsalary empsalary);

    List<Empsalary> query(String name,String SWorkId,String salDate);
}
