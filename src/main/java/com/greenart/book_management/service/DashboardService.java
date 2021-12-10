package com.greenart.book_management.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.greenart.book_management.mapper.DashboardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired DashboardMapper mapper;

    public Map<String, Object> getCounts() {
        List<Integer> bookCntList = new ArrayList<Integer>();
        bookCntList.add(mapper.getTotalBookCnt());
        bookCntList.add(mapper.getPossibleBookCnt());
        bookCntList.add(mapper.getImpossibleBookCnt());
        
        List<Integer> memberCntList = new ArrayList<Integer>();
        memberCntList.add(mapper.getTotalMemberCnt());
        memberCntList.add(mapper.getNewMemberCnt());
        memberCntList.add(mapper.getStopMemberCnt());
        memberCntList.add(mapper.getLeaveMemberCnt());

        List<Integer> bookRentalCntList = new ArrayList<Integer>();
        bookRentalCntList.add(mapper.getRentalBookCnt());
        bookRentalCntList.add(mapper.getReturnBookCnt());

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("book", bookCntList);
        map.put("member", memberCntList);
        map.put("rental", bookRentalCntList);

        return map;
    }
}
