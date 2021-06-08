package com.tianye.community.community.controller;


import com.tianye.community.community.mapper.UserMapper;
import com.tianye.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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
        System.out.println("cookies: " + Arrays.toString(cookies));
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    // 如果database中能找到该token的user，那么实例化为user对象，否则user=null
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        // 如果验证成功，设置session，这样index.html中才会显示  “我”
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
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
