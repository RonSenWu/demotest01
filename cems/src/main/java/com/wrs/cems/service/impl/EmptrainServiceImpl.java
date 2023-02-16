package com.wrs.cems.service.impl;


import com.wrs.cems.bean.Emptrain;
import com.wrs.cems.mapper.EmptrainMapper;
import com.wrs.cems.service.IEmptrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmptrainServiceImpl implements IEmptrainService {
    @Autowired
    EmptrainMapper emptrainMapper;

    @Override
    public Emptrain getEmptrainById(Integer TId) {
        return emptrainMapper.getEmptrainById(TId);
    }

    @Override
    public List<Emptrain> getAllEmptrains() {
        return emptrainMapper.getAllEmptrains();
    }

    @Override
    public List<Emptrain> getEmptrainAndEmp() {
        return emptrainMapper.getEmptrainAndEmp();
    }

    @Override
    public int deleteEmptrainById(Integer TId) {
        return emptrainMapper.deleteEmptrainById(TId);
    }

    @Override
    public int addEmptrain(Emptrain emptrain) {
        return emptrainMapper.addEmptrain(emptrain);
    }

    @Override
    public int updateEmptrain(Emptrain emptrain) {
        return emptrainMapper.updateEmptrain(emptrain);
    }

    @Override
    public List<Emptrain> query(String name,String TWorkId,String deptname) {
        return emptrainMapper.query(name,TWorkId,deptname);
    }
}
