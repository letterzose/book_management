package com.greenart.book_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.greenart.book_management.data.MemberHistoryVO;
import com.greenart.book_management.data.MemberVO;
import com.greenart.book_management.mapper.MemberMapper;
import com.greenart.book_management.utils.AESAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired MemberMapper mapper;
    public Map<String, Object> addMemberInfo(MemberVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(data.getMi_name().equals("") || data.getMi_name() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "name");
            resultMap.put("message", "이름을 입력해주세요");
            return resultMap;
        }
        if(data.getMi_birth().equals("") || data.getMi_birth() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "birth");
            resultMap.put("message", "생년월일을 입력해주세요");
            return resultMap;
        }
        if(data.getMi_phone_num().equals("") || data.getMi_phone_num() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "phone");
            resultMap.put("message", "전화번호를 입력해주세요");
            return resultMap;
        }
        if(data.getMi_email().equals("") || data.getMi_email() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "email");
            resultMap.put("message", "이메일을 입력해주세요");
            return resultMap;
        }

        String pwd = data.getMi_pwd();
        String encrypted = AESAlgorithm.Encrypt(pwd);
        data.setMi_pwd(encrypted);

        mapper.addMemberInfo(data);

        MemberHistoryVO history = new MemberHistoryVO();
        history.setMih_type("new");
        history.setMih_content(data.makeHistoryStr());
        Integer recent_seq = mapper.getRecentAddedMemberSeq();
        history.setMih_bi_seq(recent_seq);

        mapper.insertMemberHistory(history);

        resultMap.put("status", true);
        resultMap.put("message", "회원 정보가 추가되었습니다.");
        return resultMap;
    }

    public Map<String, Object> getMemberList(String type, String keyword, Integer offset) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(keyword == null) {
            resultMap.put("keyword", keyword);
            keyword = "%%";      
        }
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%"; 
        }

        resultMap.put("type", type);
        if(offset == null) offset = 0;

        List<MemberVO> list = mapper.getMemberList(type, keyword, offset);
        Integer cnt = mapper.getMemberCnt(type, keyword);

        Integer page = cnt / 10;
        if(cnt % 10 > 0) page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

        return resultMap;
    }
    
    public ResponseEntity<Map<String, Object>> deleteMemberInfo(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        Integer cnt = mapper.isExistMember(seq);
        if(cnt == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "삭제에 실패했습니다. (존재하지 않는 교직원 정보)");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        else {
            mapper.deleteMemberInfo(seq);
            resultMap.put("status", true);
            resultMap.put("message", "삭제했습니다.");

            MemberHistoryVO history = new MemberHistoryVO();
            history.setMih_type("delete");
            history.setMih_bi_seq(seq);
            mapper.insertMemberHistory(history);

            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
    }

    public MemberVO getMemberInfoBySeq(Integer seq) {
        return mapper.getMemberInfoBySeq(seq);
    }

    public Map<String, Object> patchMemberInfo(MemberVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateMemberInfo(data);
        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");
        
        MemberHistoryVO history = new MemberHistoryVO();
        history.setMih_type("modify");
        history.setMih_content(data.makeHistoryStr());
        history.setMih_bi_seq(data.getMi_seq());
        mapper.insertMemberHistory(history);

        return resultMap;
    }
}
