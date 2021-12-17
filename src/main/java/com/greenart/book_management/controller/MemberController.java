package com.greenart.book_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/member")
    public String getMemberList() {
        return "/member/list";
    }
}
