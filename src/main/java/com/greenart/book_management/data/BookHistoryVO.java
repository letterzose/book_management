package com.greenart.book_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class BookHistoryVO {
    private Integer bookh_seq;	
    private String bookh_type;	
    private String bookh_content;	
    private Date bookh_reg_dt;	
    private Integer bookh_bi_seq;
}
