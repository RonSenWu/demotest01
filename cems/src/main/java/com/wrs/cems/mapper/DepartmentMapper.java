package com.wrs.cems.mapper;


import com.wrs.cems.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 指定这是一个操作数据库的Mapper
@Mapper
@Repository
public interface DepartmentMapper {

//    根据id查询部门
    Department getDeptById(@Param("id") Integer id);

    Department findDeptByName(String name);

//    查询所有部门
    List<Department> getAllDepts();

    Department getAllName();

/*//    根据id删除部门
    int deleteDeptById(Integer id);*/

/*//    添加部门
    int addDept(Department dept);*/

/*//    更新部门
//    @Update("update dept set name=#{name} where id=#{id}")
    int updateDept(Department dept);*/
}
