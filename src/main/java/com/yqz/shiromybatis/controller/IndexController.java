package com.yqz.shiromybatis.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping({"/","/index"})
    public String toIdex(Model model){
        System.out.println("index");
        model.addAttribute("msg","hello shiro");
         return "index";
    }

    @RequestMapping({"/err403.html","/err403"})
    public String err403(){
        System.out.println("403");
        return "err403";
    }


    @RequestMapping({"/login.html","/tologin"})
    public String toLogin(Model model){
        System.out.println("tologin");
        model.addAttribute("msg","需要登录");
        return "login";
    }








    @RequestMapping({"/login.html","/login"})
    public String login(Model model, @RequestParam("username") String username,@RequestParam("password") String password, HttpSession session){
        System.out.println(username+password);
        session.setAttribute("username",username);
        //        获取当前用户
        Subject subject = SecurityUtils.getSubject();

//        封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);



        try{
            subject.login(token);//执行登录的方法，如果没有异常就ok
            return "index";
        }catch (UnknownAccountException e){     //用户名错误
                   model.addAttribute("msg","用户名错误");
                   return "login";
        }catch (IncorrectCredentialsException e){   //密码错误
                model.addAttribute("mag","密码错误");
                return "login";
        }



    }

    @RequestMapping("/user/{path}")
    public String user(@PathVariable("path") String path){

        return "/user/"+path;

    }

    @RequestMapping("/401")
    public String unAuthorized(){
                return "err401";
    }



}
