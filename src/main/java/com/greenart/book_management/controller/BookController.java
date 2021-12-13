package com.greenart.book_management.controller;

import java.util.Map;

import com.greenart.book_management.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    @Autowired BookService service;
    @GetMapping("/book")
    public String getBook(Model model, @RequestParam @Nullable Integer offset) {
        Map<String, Object> resultMap = service.getBookList(offset);
        model.addAttribute("data", resultMap);
        
        return "/book/list";
    }
}
