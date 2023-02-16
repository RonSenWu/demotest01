package com.wrs.cems.mapper;

import com.wrs.cems.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User login(User user);

    User findById(Integer id);

    List<User> getAllUsers();

    int updatePassword(@Param("id") Integer id, @Param("password") String password) ;
    int updateUsername(@Param("id") Integer id,@Param("username") String username);

    int deleteUserById(Integer id);

    int addUser(User user);

    int updateUser(User user);
    List<User> query(String username,String role);
}
