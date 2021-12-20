package com.greenart.book_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
    private Integer mi_seq;	
    private Integer mi_bi_seq;	
    private String mi_name;	
    private String mi_birth;	
    private String mi_pwd;
    private String mi_phone_num;	
    private String mi_email;	
    private Date mi_reg_dt;	
    private Date mi_mod_dt;	
    private Integer mi_status;
    private String book_title;
}
