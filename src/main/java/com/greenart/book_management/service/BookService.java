package com.greenart.book_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.greenart.book_management.data.BookVO;
import com.greenart.book_management.mapper.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired BookMapper mapper;

    public Map<String, Object> getBookList(Integer offset) {
        if(offset == null) offset = 0;
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<BookVO> list = mapper.getBookInfo(offset);

        Integer cnt = mapper.getBookCount();
        Integer page_cnt = cnt / 10;
        if(cnt % 10 > 0) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addBook(BookVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(data.getBi_title() == null || data.getBi_title().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "도서명을 입력하세요.");
            return resultMap;
        }
        if(data.getBi_author() == null || data.getBi_author().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "저자를 입력하세요.");
            return resultMap;
        }
        if(data.getBi_price() == null || data.getBi_price() == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "가격을 입력하세요.");
            return resultMap;
        }
        if(data.getBi_description() == null || data.getBi_description().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "설명을 입력하세요.");
            return resultMap;
        }
        if(data.getBi_stock() == null || data.getBi_stock() == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "재고를 입력하세요.");
            return resultMap;
        }
        mapper.addBook(data);
        resultMap.put("status", true);
        resultMap.put("message", "도서가 등록되었습니다.");
        return resultMap;
    }
    public Map<String, Object> deleteBook(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteBook(seq);
        resultMap.put("status", true);
        resultMap.put("message", "도서가 삭제되었습니다.");
        return resultMap;
    }
}
