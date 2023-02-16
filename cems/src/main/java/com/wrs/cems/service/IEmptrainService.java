package com.wrs.cems.service;



import com.wrs.cems.bean.Emptrain;

import java.util.List;

public interface IEmptrainService {
    Emptrain getEmptrainById(Integer TId);

    List<Emptrain> getAllEmptrains();

    List<Emptrain> getEmptrainAndEmp();

    int deleteEmptrainById(Integer TId);

    int addEmptrain(Emptrain emptrain);

    int updateEmptrain(Emptrain emptrain);

    List<Emptrain> query(String name,String TWorkId,String deptname);
}
