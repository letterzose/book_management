package com.greenart.book_management.controller;

import com.greenart.book_management.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    @Autowired MemberService service;
    @GetMapping("/member")
    public String getMemberList(Model model,
        @RequestParam @Nullable String type,
        @RequestParam @Nullable String keyword,
        @RequestParam @Nullable Integer offset
    ) {
        model.addAttribute("data", service.getMemberList(type, keyword, offset));
        return "/member/list";
    }
}
