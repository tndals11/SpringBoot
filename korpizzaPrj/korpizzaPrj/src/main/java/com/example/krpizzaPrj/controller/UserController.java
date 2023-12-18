package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.dto.AskDto;
import com.example.krpizzaPrj.dto.UserDto;
import com.example.krpizzaPrj.mappers.UserMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/common/register")
    public String getRegister() {

        return "common/register";
    }
    // 회원가입 페이지
    @PostMapping("/common/register")
    @ResponseBody
    public Map<String, Object> setRegister(@ModelAttribute UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        if (userDto != null) {
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
    public String checkLogin(@RequestBody UserDto userDto, HttpServletRequest request) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단
        UserDto userdto = userMapper.checkLogin(userDto);
        System.out.println(userdto);
        String msg = "";
        if (userdto != null ) {
            if ("90".equals(userdto.getUserSt())) {
                System.out.println("1231313132");
                msg = "userDelete";
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("user", userdto);
                msg = "success";
            }
        }  else {
            msg = "fail";
        }
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
    @PostMapping("/common/findPw")
    public String checkFindPw(@ModelAttribute UserDto userDto, Model model, RedirectAttributes ra) { // Model <- 백단  // @ModelAttribute 뷰단 -> 백단
        UserDto res = userMapper.checkFindPw(userDto);
        if ( res != null ) {
           String userPasswd = res.getUserPasswd();
            ra.addFlashAttribute("msg",userPasswd );
            return "redirect:/common/viewFindPw";
        } else {
            ra.addFlashAttribute("msg", "아이디와 이메일을 다시 확인해주세요");
            return "redirect:/common/findPw";
        }
    }

    // 비밀번호 찾기 후 뷰단에 보여주는 페이지 
    @GetMapping("/common/viewFindPw")
    public String viewFindPw(HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");
        return "common/viewFindPw";
    }

    @GetMapping("/user/goupDatePage")
    public String goupDatePage() {

        return "user/goupDatePage";
    }
    @PostMapping("/user/goupDatePage")
    @ResponseBody
    public Map<String, Object> setGoupDatePage(@RequestParam String userEmail, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        UserDto dto = (UserDto) session.getAttribute("user");
        System.out.println(dto);
        int userName = dto.getUserNum();
        int result =  userMapper.setGoupDatePage( userEmail , userName);
        System.out.println(userName);
        System.out.println(result);
        if ( result > 0 ) {
            map.put("msg", "success");
        } else {
            map.put("msg", "fail");
        }

        System.out.println(map);
        return map;
    }


    @GetMapping("/user/updateUserInfo")
    public String getupdateUserInfoPage(Model model, HttpSession session) {
        UserDto dto = (UserDto) session.getAttribute("user");
        model.addAttribute("userId",dto.getUserId());
        model.addAttribute("userName",dto.getUserName());
        model.addAttribute("userEmail", dto.getUserEmail());


        return "user/updateUserInfo";
    }

    @PostMapping("/user/updateUserInfo")
    @ResponseBody
    public Map <String, Object> setupdateUserInfoPage(@RequestParam String userName, HttpSession session) {
        UserDto dto = (UserDto) session.getAttribute("user");
        System.out.println(dto);
        System.out.println(userName);
        int userNum = dto.getUserNum();
        Map <String, Object> map = new HashMap<>();

        if ( userName.equals(dto.getUserName())) {
            map.put("msg", "fail");
        } else {
            userMapper.setupdateUserInfo(userName, userNum);
            map.put("msg", "success");
        }
        return map;
    }

    @PostMapping("/user/deleteUserInfo")
    @ResponseBody
    public Map<String, Object> deleteUserInfo(Model model, HttpSession session) {
        UserDto dto = (UserDto) session.getAttribute("user");
        int userNum = dto.getUserNum();
        String userSt = dto.getUserSt();
        userMapper.deleteUserInfo(userNum);
        Map<String, Object> map = new HashMap<>();

        if ( "90".equals(userSt) ) {
            map.put("msg", "fail");

        } else {
            map.put("msg", "success");
        }
        return map;
    }


    @GetMapping("/user/updatePasswd") public String getUserUpdatePasswd() {

        return "/user/updatePasswd";
    }

    @PostMapping("/user/updatePasswd")
    @ResponseBody
    public Map <String, Object> UserUpdatePasswd(@RequestParam String newPasswd ,HttpSession session) {
        Map <String, Object> map = new HashMap<>();
        UserDto dto = (UserDto) session.getAttribute("user");
        int userNum = dto.getUserNum();
        if (newPasswd.equals(dto.getUserPasswd())) {
            map.put("msg", "fail");
        } else {
            userMapper.updateUserPasswd(newPasswd,userNum);
            map.put("msg", "success");
        }
        System.out.println(map);
        return map;
    }


    @GetMapping("/common/mainPage")
    public String getMainPage() {

        return "common/mainPage";
    }
    @GetMapping("/user/afterLogin")
    public String getAfterLogin(Model model) {
        return "user/afterLogin";
    }

    @GetMapping("/common/map")
        public String getMap() {

            return "common/map";
        }
        @GetMapping("/common/eventPage") public String getEventPage() {
            return "common/eventPage";
        }
        @GetMapping("/common/endEventPage") public String getEndEventPage() {
            return "common/endEventPage";
        }
        @GetMapping("/common/original") public String getOriginal() {
            return "common/original";
        }

    @GetMapping("/user/userinfo") public String getUserInfo() {

        return "user/userinfo";
    }

    @GetMapping("/user/ask")
    public String userAsk(HttpSession session, Model model) {
        UserDto dto = (UserDto) session.getAttribute("user");
        model.addAttribute("userName", dto.getUserName());
        model.addAttribute("userEmail", dto.getUserEmail());
        model.addAttribute("userId", dto.getUserId());

        return "user/ask";
    }

    @PostMapping("/user/ask")
    public String setuserAsk(@ModelAttribute AskDto askDto, @RequestPart(value = "file", required = false) MultipartFile mf ) throws IOException {

        System.out.println(askDto);
        if (!mf.isEmpty()) {

            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

            File makeFolder = new File(fileDir + folderName);


            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }

            String orgName = mf.getOriginalFilename();
            String ext = orgName.substring(orgName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();

            String saveFileName = uuid + ext;

            String savedFilePathName = fileDir + folderName + "/" + saveFileName;

            //boardDto -> db
            askDto.setOrgName(orgName);
            askDto.setSavedFileName(saveFileName);
            askDto.setSavedFilePathName(savedFilePathName);
            askDto.setSavedFileSize(mf.getSize());
            askDto.setFolderName(folderName);
            askDto.setExt(ext);

            //파일 업로드 쓰기
            mf.transferTo(new File(savedFilePathName));
        }


        return "user/ask";
    }
}