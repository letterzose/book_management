package com.greenart.book_management.mapper;

import java.util.List;

import com.greenart.book_management.data.BookVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    public List<BookVO> getBookInfo(Integer offset);
    public Integer getBookCount();
    public void addBook(BookVO data);
    public void deleteBook(Integer seq);
}
