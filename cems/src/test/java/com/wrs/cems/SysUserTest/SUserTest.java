package com.wrs.cems.SysUserTest;

import com.wrs.cems.bean.User;
import com.wrs.cems.mapper.UserMapper;
import com.wrs.cems.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.events.Event;

@SpringBootTest
public class SUserTest {
    @Autowired
    private IUserService userService;

    @Autowired
    private  UserMapper userMapper;

    @Test
    public void getSysUser(){
        User user = new User();
        user.setUsername("wrs");
        user.setPassword("123456");
        user.setEnabled(1);
        userService.addUser(user);
        System.out.println("user="+user);
    }

    @Test
    public void updatePwd() throws Exception {
        int id = 5;
        String newPwd ="123";
        String username ="超管员1";
        Integer row=userMapper.updatePassword(id,newPwd);
        System.out.println(row);
    }

    @Test
    public void  getUser() throws Exception {
        int id=5;
        User user = userMapper.findById(id);
        System.out.println(user);
    }
    @Test
    public void changePwd(){
        int id =1;
        String username = "admin";
        String oldPassword="123";
        String newPassword="123456";
        try {
            userService.changePassword(id,username,oldPassword,newPassword);
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }
}
