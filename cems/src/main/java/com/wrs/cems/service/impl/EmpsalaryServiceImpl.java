package com.wrs.cems.service.impl;


import com.wrs.cems.bean.Empsalary;
import com.wrs.cems.mapper.EmpsalaryMapper;
import com.wrs.cems.service.IEmpsalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpsalaryServiceImpl implements IEmpsalaryService
{
    @Autowired
    private EmpsalaryMapper empsalaryMapper;

    @Override
    public Empsalary getEmpsalaryById(Integer id) {
        return empsalaryMapper.getEmpsalaryById(id);
    }

    @Override
    public List<Empsalary> getAllEmpsalarys() {
        return empsalaryMapper.getAllEmpsalarys();
    }

    @Override
    public List<Empsalary> getAll() {
        return empsalaryMapper.getAll();
    }

    @Override
    public void deleteEmpsalaryById(Integer id) {
        empsalaryMapper.deleteEmpsalaryById(id);
    }

    @Override
    public int addEmpsalary(Empsalary empsalary) {
        int allSalary= empsalary.getBasicSal()+empsalary.getBonus()+empsalary.getPerk()-empsalary.getForfeit();
        empsalary.setAccountSal(allSalary);
        return empsalaryMapper.addEmpsalary(empsalary);
    }

    @Override
    public void updateEmpsalary(Empsalary empsalary) {
        int allSalary= empsalary.getBasicSal()+empsalary.getBonus()+empsalary.getPerk()-empsalary.getForfeit();
        empsalary.setAccountSal(allSalary);
        empsalaryMapper.updateEmpsalary(empsalary);
    }

    @Override
    public List<Empsalary> query(String name,String SWorkId,String salDate) {
        if (salDate != null && !salDate.equals("") && salDate.length() < 10) {
            salDate = "%" + salDate + "%";
        }
        return empsalaryMapper.query(name,SWorkId,salDate);
    }
}
