package com.wrs.cems.controller;

import com.wrs.cems.bean.User;

import com.wrs.cems.config.Md5Util;
import com.wrs.cems.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

//    定义后台用户业务对象
    @Autowired
    private IUserService userService;

//    @RequestMapping("/")
//    public String index() {
//        return "login";
//    }

    @PostMapping("/user/login")
    public String login(HttpServletRequest request, Map<String, Object> map,
                        HttpSession session){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String pwd = Md5Util.md5Hash(password);
        User user = new User(account,pwd,role);

    //        根据用户名和密码进行登录校验
        User u = userService.login(user);

        if(u==null) {
//        登录失败
            map.put("msg","账号密码错误！");
            return "login";

        } else {
            session.setAttribute("loginUser",user.getAccount());
            session.setAttribute("username",u.getUsername());
            session.setAttribute("id",u.getId());
            session.setAttribute("role",u.getRole());
//            登录成功，防止表单重复提交，可以重定向到主页
            return "redirect:/emps";
        }

    }


    @RequestMapping("/user/logout")
    public String logout(){
        return "login";
    }



    @GetMapping("/account")
    public String toAccount(HttpSession session, Model model) throws Exception {
        Integer sid = (Integer) session.getAttribute("id");
        User user = userService.getUserById(sid);
        model.addAttribute("user",user);
        return "user/account";
    }

    @RequestMapping("/account")
    public String updateUser(@RequestParam("username") String username, HttpSession session) throws Exception
    {
        Integer sid = (Integer) session.getAttribute("id");
        userService.updateUsername(sid,username);
        session.setAttribute("username",username);
        return "redirect:/emps";
    }
    //来到修改页面
    @GetMapping("/updatePwd")
    public String toUpdatePage(HttpSession session, Model model) throws Exception {
        Integer sid = (Integer) session.getAttribute("id");
        User user = userService.getUserById(sid);
        model.addAttribute("user",user);
        return "user/updatePwd";
    }

    // 修改，需要提交id
    @RequestMapping("/updatePwd")
    public String updateUser(@RequestParam(value = "old_password",required = false) String oldpassword,
                             @RequestParam(value = "new_password",required = false) String newPassword,
                             HttpSession session,Model model) throws Exception
    {
        Integer sid = (Integer) session.getAttribute("id");
        String account = session.getAttribute("loginUser").toString();
        String oldPwd = Md5Util.md5Hash(oldpassword);
        String newPwd = Md5Util.md5Hash(newPassword);
        System.out.println("oldPwd="+oldPwd);
        System.out.println("newPwd="+newPwd);
        model.addAttribute("oldpassword",oldPwd);
        userService.changePassword(sid,account,oldPwd,newPwd);

        return "login";
    }

}
