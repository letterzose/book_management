package com.greenart.book_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class MemberHistoryVO {
    private Integer mih_seq;	
    private Integer mih_bi_seq;	
    private String mih_type;	
    private String mih_content;
    private Date mih_reg_dt;
}
