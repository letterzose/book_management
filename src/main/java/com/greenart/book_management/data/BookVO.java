package com.greenart.book_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class BookVO {
    private Integer bi_seq;	
    private String bi_title;	
    private String bi_author;	
    private Integer bi_price;	
    private String bi_description;	
    private Integer bi_stock;	
    private String bi_image;	
    private Integer bi_category;	
    private Date bi_reg_dt;	
    private Date bi_mod_dt;	
    private Integer bi_status;
}
