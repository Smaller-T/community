package com.tianye.community.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//意思是允许这个类去接收前端的 一个请求，将IndexController识别为Spring的一个bean
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
//    public String hello(@RequestParam(name = "name") String name, Model model) {
//        //将浏览器传过来的值传入Model中
//        model.addAttribute("name", name);
//        return "index";   //这个hello会去templates文件夹找
//
//    }
}
