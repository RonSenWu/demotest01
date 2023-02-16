package com.wrs.cems.service;



import com.wrs.cems.bean.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IContractService {
    Contract getContractById(Integer CId);

    List<Contract> getContractAndEmp();

    List<Contract> getContract();

    int deleteContractById(Integer CId);

    int addContract(Contract contract);

    int updateContract(Contract contract);

    int updateFile(@Param("CId") Integer CId, String contractContent);

    List<Contract> query(String name,String CWorkId);
}
