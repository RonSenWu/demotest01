package com.wrs.cems.mapper;



import com.wrs.cems.bean.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContractMapper {
    //    根据id查询合同
    Contract getContractById(Integer CId);

    //   查询所有合同和员工
    List<Contract> getContractAndEmp();

    //    查询所有合同
    List<Contract> getAllContracts();

    //    根据id删除合同
    int deleteContractById(Integer CId);

    //    添加合同
    int addContract(Contract contract);

    //    修改合同信息
    int updateContract(Contract contract);

    //    修改合同文件信息
    int updateFile(@Param("CId") Integer CId , String contractContent);

    List<Contract> query(String name,String CWorkId);
}
