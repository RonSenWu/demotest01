package com.wrs.cems.service.impl;

import com.wrs.cems.bean.User;
import com.wrs.cems.mapper.UserMapper;
import com.wrs.cems.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
    @Override
    public User getUserById(Integer id) throws Exception {
        User users = userMapper.findById(id);
        // 判断查询结果是否为null
        if (users == null) {
            // 是：抛出Exception
            throw new Exception(
                    "获取用户信息失败！尝试访问的用户不存在！");
        }
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void updateUser(User user) throws Exception {
        User result = userMapper.findById(user.getId());
        if (result == null) {
            // 是：抛出UserNotFoundException
            throw new Exception(
                    "修改密码失败！尝试访问的用户不存在！");
        }
      userMapper.updateUser(user);
    }

    @Override
    public List<User> query(String username,String role) {
        return userMapper.query(username,role);
    }

    @Override
    public void changePassword(Integer id, String username, String oldPassword, String newPassword) throws Exception {
        User result = userMapper.findById(id);
        if (result ==null){
            throw new Exception("修改密码失败,尝试访问的用户不存在！");
        }
        if (!oldPassword.equals(result.getPassword())){
            throw new Exception("修改密码错误，输入密码错误");
        }
        userMapper.updatePassword(id,newPassword);

    }

    @Override
    public int updateUsername(Integer id,String username) throws Exception {
         return userMapper.updateUsername(id,username);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }



}
