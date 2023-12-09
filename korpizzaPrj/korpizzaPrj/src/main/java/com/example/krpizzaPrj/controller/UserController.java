package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.dto.UserDto;
import com.example.krpizzaPrj.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

        @Autowired
        UserMapper userMapper;


        @GetMapping("/common/register")
        public String getRegister() { 

            return "common/register";
        }

        @PostMapping("/common/register")
        @ResponseBody
        public Map<String, Object> setRegister(@ModelAttribute UserDto userDto) {
            System.out.println(userDto);
            Map<String, Object> map = new HashMap<>();
            if( userDto != null ) {
                userMapper.setRegister(userDto);
                map.put("msg", "회원가입이 완료되었습니다.");
            }
            return map;
        }

        @GetMapping("/common/checkUserId")
        @ResponseBody
        public Map<String, Object> getCheckUserId(@RequestParam String userId) {
            int CheckUserId = userMapper.getCheckUserId(userId);

            return Map.of("checkUserId", CheckUserId);
        }

        @GetMapping("/common/checkUserEmail")
        @ResponseBody
        public Map<String, Object> getCheckUserEmail(@RequestParam String userEmail) {
            int CheckUserEmail = userMapper.getCheckUserEmail(userEmail);

            return Map.of("CheckUserEmail", CheckUserEmail);
        }

        @GetMapping("/common/findID")
        public String getFindID() {

            return "common/findID";
        }
        @GetMapping("/common/findPw")
        public String getFindPw() {

            return "common/findPw";
        }



        @GetMapping("/common/login")
        public String getLogin() {

            return "common/login";
        }

    @PostMapping("/common/checkLogin")
    @ResponseBody
    public String checkLogin(@RequestBody UserDto userDto) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단

        UserDto checkLogin = userMapper.checkLogin(userDto);
        System.out.println(userDto);

        String msg = "";
        if( checkLogin != null ) {
            msg = "success";
        } else {
            msg = "fail";
        }
        System.out.println(msg);
        return msg;
    }

        @PostMapping("/common/checkFindID")
        @ResponseBody
        public String checkFindID(@RequestBody UserDto userDto) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단

            UserDto result = userMapper.checkFindID(userDto);
            System.out.println(userDto);

            String msg = "";
            if ( result != null) {
                msg = "success";
            } else {
                msg = "fail";
                System.out.println(msg);
            }
            return msg;
        }

    @GetMapping("/common/mainPage")
    public String getMainPage() {

        return "common/mainPage";
    }
    @GetMapping("/user/afterLogin")
    public String getAfterLogin() {

        return "user/afterLogin";
    }

    @GetMapping("/common/map")
        public String getMap() {

            return "common/map";
        }
        @GetMapping("/common/eventPage")
        public String getEventPage() {
            return "common/eventPage";
        }
        @GetMapping("/common/endEventPage")
        public String getEndEventPage() {
            return "common/endEventPage";
        }
        @GetMapping("/common/original")
        public String getOriginal() {
            return "common/original";
        }
}