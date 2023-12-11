package com.example.krpizzaPrj.controller;

import com.example.krpizzaPrj.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired
    AdminMapper adminMapper;


    @GetMapping("/admin/userAdminPage")
    public String getUserAdminPage(Model model, @RequestParam(defaultValue = "") String searchType, @RequestParam(defaultValue = "") String words ) {

        String queryString = "";
        if( searchType.equals("userid") ) {
            queryString = "WHERE user_id = '"+words+"'";
        }else if( searchType.equals("username")  ) {
            queryString = "WHERE user_name = '"+words+"'";
        }else{
            queryString = "";
        }
        model.addAttribute("cnt", adminMapper.getUserAdminCount(queryString));
        model.addAttribute("mem", adminMapper.getUserAdminPage(queryString));
        return "admin/userAdminPage";
    }

    @GetMapping("/admin/viewUser")
    public String getViewUser(@RequestParam int id, Model model) {


        model.addAttribute("mem", adminMapper.getViewUser(id));
        return "admin/viewUser";
    }

    @GetMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam int id, RedirectAttributes ra) {

        adminMapper.deleteUser(id);
        ra.addFlashAttribute("msg", "회원이 삭제되었습니다.");

        return "redirect:/admin/userAdminPage";
    }
}
