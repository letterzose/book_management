package com.greenart.book_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.greenart.book_management.data.BookHistoryVO;
import com.greenart.book_management.data.BookVO;
import com.greenart.book_management.mapper.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired BookMapper mapper;

    public Map<String, Object> getBookList(Integer offset, String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(offset == null) {
            offset = 0;
            resultMap.put("offset", offset);
        }
        if(keyword == null) {
            keyword = "%%";
            resultMap.put("keyword", "");
        }
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }
        List<BookVO> list = mapper.getBookInfo(offset, keyword);

        Integer cnt = mapper.getBookCount(keyword);
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

        Integer seq = mapper.selectLatestDataSeq();
        BookHistoryVO history = new BookHistoryVO();
        history.setBookh_bi_seq(seq);
        history.setBookh_type("new");
        String content = data.getBi_title()+"|"+data.getBi_author()+"|"+data.getBi_price()+"|"+
        data.getBi_description()+"|"+data.getBi_stock()+"|"+data.getBi_category()+"|"+data.getBi_status();
        history.setBookh_content(content);

        mapper.insertBookHistory(history);

        return resultMap;
    }
    public Map<String, Object> deleteBook(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteBook(seq);
        resultMap.put("status", true);
        resultMap.put("message", "도서가 삭제되었습니다.");

        BookHistoryVO history = new BookHistoryVO();
        history.setBookh_bi_seq(seq);
        history.setBookh_type("delete");

        mapper.insertBookHistory(history);

        return resultMap;
    }

    public Map<String, Object> getBookInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("status", true);
        resultMap.put("data", mapper.getBookInfoBySeq(seq));
        return resultMap;
    }

    public Map<String, Object> updateBookInfo(BookVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.updateBook(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        BookHistoryVO history = new BookHistoryVO();
        history.setBookh_bi_seq(data.getBi_seq());
        history.setBookh_type("update");
        String content = data.getBi_title()+"|"+data.getBi_author()+"|"+data.getBi_price()+"|"+
        data.getBi_description()+"|"+data.getBi_stock()+"|"+data.getBi_category()+"|"+data.getBi_status();
        history.setBookh_content(content);

        mapper.insertBookHistory(history);

        return resultMap;
    }

    public Map<String, Object> getBookByKeyword(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(keyword == null) keyword= "%%";
        keyword = "%"+keyword+"%";

        List<BookVO> list = mapper.getBookByKeyword(keyword);

        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }
}
