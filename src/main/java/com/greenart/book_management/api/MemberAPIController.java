package com.greenart.book_management.api;

import java.util.Map;

import com.greenart.book_management.data.MemberVO;
import com.greenart.book_management.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {
    @Autowired MemberService service;
    @PostMapping("/member/add")
    public Map<String, Object> postMemberAdd(@RequestBody MemberVO data) throws Exception{
        return service.addMemberInfo(data);
    }
    @DeleteMapping("/member/delete")
    public ResponseEntity<Map<String, Object>> deleteMemberInfo(@RequestParam Integer seq) {
        return service.deleteMemberInfo(seq);
    }
    @GetMapping("/member/get")
    public MemberVO getMemberInfoBySeq(@RequestParam Integer seq) {
        return service.getMemberInfoBySeq(seq);
    }
    @PatchMapping("/member/modify")
    public Map<String, Object> patchMemberInfo(@RequestBody MemberVO data) {
        return service.patchMemberInfo(data);
    }
}
