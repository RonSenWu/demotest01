package com.wrs.cems.service.impl;


import com.wrs.cems.bean.Contract;
import com.wrs.cems.mapper.ContractMapper;
import com.wrs.cems.service.IContractService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ContractServiceImpl implements IContractService {
    @Autowired
    private ContractMapper contractMapper;

    @Override
    public Contract getContractById(Integer CId) {
        return contractMapper.getContractById(CId);
    }


    @Override
    public List<Contract> getContractAndEmp() {
        return contractMapper.getContractAndEmp();
    }

    @Override
    public List<Contract> getContract() {
        return contractMapper.getAllContracts();
    }

    @Override
    public int deleteContractById(Integer CId) {
        return contractMapper.deleteContractById(CId);
    }

    @Override
    public int addContract(Contract contract) {
        return contractMapper.addContract(contract);
    }

    @Override
    public int updateContract(Contract contract) {
        return contractMapper.updateContract(contract);
    }

    @Override
    public int updateFile(Integer CId, String contractContent) {
        return contractMapper.updateFile(CId,contractContent);
    }

    @Override
    public List<Contract> query(String name,String CWorkId) {
        return contractMapper.query(name,CWorkId);
    }
}
