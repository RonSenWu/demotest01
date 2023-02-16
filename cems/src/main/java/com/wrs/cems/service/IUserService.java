package com.wrs.cems.service;

import com.wrs.cems.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IUserService {
    //定义用户登录方法的业务接口
    User login(User user);

    User getUserById(Integer id) throws Exception;

    List<User> getAllUsers();
    //更新密码
    void changePassword(
            Integer id, String username,
            String oldPassword, String newPassword) throws Exception;

    int updateUsername(Integer id,String username) throws  Exception;

    int deleteUserById(Integer id);

    int addUser(User user);

    void updateUser(User user) throws Exception;
    List<User> query(String username,String role);
}
