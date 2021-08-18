package com.dfby.controller;

import com.dfby.pojo.User;
import com.dfby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginControl {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public String list(User user,HttpSession httpSession) {
//        String msg="";
//        System.out.println("aaaaa==============");
//        if(userService.selectUser(user)){
//            System.out.println("bbbbb==============");
//            msg="登录成功";
//            httpSession.setAttribute( "User" , user);
//            model.addAttribute("msg",msg);
//            return "index";
//        }else{
//            msg="登录失败";
//            model.addAttribute("msg",msg);
//            return "login";
//        }
        User user1=userService.checkLogin(user);
        if(user1!=null){
            httpSession.setAttribute( "User" , user1);
            return "success";
        }else{
            return "error";
        }
  }


    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("User");
        return "login";
    }
}
