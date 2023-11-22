package com.example.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {


    @GetMapping("/common")
    public String getCommon(){

        return "common/main";
    }

    @GetMapping("/common/test")
    public String getTest(){

        return "common/test";
    }

}
