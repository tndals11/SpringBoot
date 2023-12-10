package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.dto.UserDto;
import com.example.krpizzaPrj.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;


    @GetMapping("/common/register")
    public String getRegister() {

        return "common/register";
    }
    // 회원가입 페이지
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

    // 아이디 중복성 체크
    @GetMapping("/common/checkUserId")
    @ResponseBody
    public Map<String, Object> getCheckUserId(@RequestParam String userId) {
        int CheckUserId = userMapper.getCheckUserId(userId);

        return Map.of("checkUserId", CheckUserId);
    }

    // 이메일 중복성 체크
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


    // 로그인 페이지 
    @GetMapping("/common/login")
    public String getLogin() {

        return "common/login";
    }
    
    // 로그인 페이지 값을 받아와 처리 
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
    
    // 아이디 찾기 페이지 값을 받아와 처리
    @PostMapping("/common/checkFindID")
    @ResponseBody
    public Map<String, Object> checkFindID(@RequestBody UserDto userDto) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단

        UserDto result = userMapper.checkFindID(userDto);
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        if ( result != null ) {
            msg = "success";
            map.put("userId", result.getUserId() ); // url 담을려고 변수명 userId에 getUserId를 받아옴
        } else {
            msg = "fail";
        }
        map.put("msg", msg); // --> 메세지를 보낼려고 쓴것
        return map;
    }
    
    // 아이디 찾기 후 뷰단에 보여주는 페이지
    @GetMapping("/user/viewFindId")
    public String viewFindId(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "user/viewFindId";
    }

    // 비밀번호 찾기 주소
    @GetMapping("/common/findPw")
    public String getFindPw() {

        return "common/findPw";
    }

    // 비밀번호 찾기 주소 값 받기
    @PostMapping("/common/checkFindPw")
    @ResponseBody
    public Map<String, Object> checkFindPw(@RequestBody UserDto userDto) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단
        UserDto res = userMapper.checkFindPw(userDto);
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        if ( res != null ) {
            msg = "success";
            map.put("userPw", res.getUserPasswd() ); // url 담을려고 변수명 userId에 getUserId를 받아옴
        } else {
            msg = "fail";
        }
        map.put("msg", msg); // --> 메세지를 보낼려고 쓴것
        return map;
    }
    
    // 비밀번호 찾기 후 뷰단에 보여주는 페이지 
    @GetMapping("/common/viewFindPw")
    public String viewFindPw(@RequestParam String userPw, Model model) {
        System.out.println("userPw : " + userPw);
        model.addAttribute("userPw", userPw);
        return "common/viewFindPw";
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