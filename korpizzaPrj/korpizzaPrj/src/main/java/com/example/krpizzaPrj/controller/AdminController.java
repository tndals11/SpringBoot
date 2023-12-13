package com.example.krpizzaPrj.controller;


import com.example.krpizzaPrj.dto.PageDto;
import com.example.krpizzaPrj.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {
    @Autowired
    AdminMapper adminMapper;

    @GetMapping("/admin/userAdminPage")
    public String getUserAdminPage(Model model, @RequestParam(defaultValue = "") String searchType,
                                   @RequestParam(defaultValue = "") String words,
                                   @RequestParam(value = "page", defaultValue = "1")int page ) {

        String queryString = "";
        if( searchType.equals("userid") ) {
            queryString = "WHERE user_id = '"+words+"'";
        }else if( searchType.equals("username")  ) {
            queryString = "WHERE user_name = '"+words+"'";
        }else{
            queryString = "";
        }
        PageDto pageDto = new PageDto();
        int totalCount = adminMapper.totalCount();
        System.out.println("======= totalCount : " + totalCount);
        // Math.ceil() 소수점을 강제로 1로 만든다
        int totalPage = (int)Math.ceil((double) totalCount / pageDto.getPageCount());
        // 이쪽 구문에서 식오류 잘보고 체크할 것 그리고 sout이나 디버그를 잘 사용하자
        int startPage = (int) (Math.ceil( (double) page) / (pageDto.getBlockCount()) -1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        // 끝 페이지 수가 전체 페이지 수 보다 크면
        if ( endPage > totalPage ) {
            endPage = totalPage;
        }


        // dto에 값을 보내주는
        pageDto.setPage(page);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setTotalPage(totalPage);
        //limit 시작, 개수
        // LIMIT 0, 3
        // LIMIT 3, 3
        // LIMIT 6, 3
        int startNum = (page - 1) * pageDto.getPageCount();
        int offset = pageDto.getPageCount();

        System.out.println(pageDto);
        System.out.println(startNum);
        System.out.println(offset);

        model.addAttribute("cnt", adminMapper.getUserAdminCount(queryString));
        // mapper로 바로가지않고 검색어를 가지고 Service를 먼저 거쳐서 가라
        model.addAttribute("mem", adminMapper.getUserAdminPage(queryString, startNum, offset) );
        // 이쪽 구문에서 오류 발생 !! => Service를 사용했을때는 pageDto가 들어가는 것을 몰랐는데
        // page.page 구문이 실행하기 위해서는 "page"에 pageDto를 담아서 전달해주어야한다
        model.addAttribute("page", pageDto);
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
