package com.tianye.community.community.controller;


import com.tianye.community.community.mapper.UserMapper;
import com.tianye.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
//意思是允许这个类去接收前端的 一个请求，将IndexController识别为Spring的一个bean
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        // httpServletRequest 是从浏览器请求数据
        // httpServletResponse 是将数据返回到浏览器
        Cookie[] cookies = request.getCookies();   //cookie都是K，V对保存的
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }



        return "index";
    }
//    public String hello(@RequestParam(name = "name") String name, Model model) {
//        //将浏览器传过来的值传入Model中
//        model.addAttribute("name", name);
//        return "index";   //这个hello会去templates文件夹找
//
//    }
}
