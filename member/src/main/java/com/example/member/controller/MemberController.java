package com.example.member.controller;

import com.example.member.dto.MemberDto;
import com.example.member.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberMapper memberMapper;



    //
    @GetMapping("")
    public String getMemberList(Model model, @RequestParam(defaultValue = "" ) String searchType,
                                @RequestParam(defaultValue = "") String words) {
        String queryString = "";
        if ( searchType.equals("userid")) {
            queryString = "WHERE userid = '"+words+"'";
        } else if ( searchType.equals("username")) {
            queryString = "WHERE username = '"+words+"'";
        } else {
            queryString = "";
        }

         System.out.println(queryString);
        model.addAttribute("cnt", memberMapper.getMemberCount(queryString));
        model.addAttribute("member", memberMapper.getMemberList(queryString));
        return "member/list";
    }


    @GetMapping("/insert")
    public String getInsert() {
        return "member/insert";
    }


    @PostMapping("/insert")
    public String SetInsert(@ModelAttribute MemberDto memberDto, RedirectAttributes ra) {
        // post 타입 : 주소에 드러나지 않음
        // model.attribute => get방식
        memberMapper.setInsert(memberDto);
        // RedirectAttributes: 주소를 이동시킴
        // addFlashAttribute: 일회용 주소를 만들어서 사용
            ra.addFlashAttribute("msg", "회원가입이 완료되었습니다.");
        return "redirect:/member";
    }

    @GetMapping("/delete")
    public String deleteMember(@RequestParam int id, RedirectAttributes ra) {
        memberMapper.deleteMember(id);
        // "msg" => 변수의 이름을 위에 있는 코드랑 똑같아야한다.
        ra.addFlashAttribute("msg", "회원이 삭제되었습니다.");
        // redirect:/member => 삭제를 성공하면 넘어갈 주소
        return "redirect:/member";
    }
}
