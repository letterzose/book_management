package com.greenart.book_management.api;

import java.util.Map;

import com.greenart.book_management.data.MemberVO;
import com.greenart.book_management.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {
    @Autowired MemberService service;
    @PostMapping("/member/add")
    public Map<String, Object> postMemberAdd(@RequestBody MemberVO data) throws Exception{
        return service.addMemberInfo(data);
    }
}
