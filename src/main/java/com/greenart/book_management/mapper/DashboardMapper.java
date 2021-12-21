package com.greenart.book_management.mapper;
import java.util.Date;

//com/greenart/book_management/mapper
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    public Integer getTotalBookCnt();
    public Integer getPossibleBookCnt();
    public Integer getImpossibleBookCnt();

    public Integer getTotalMemberCnt();
    public Integer getNewMemberCnt();
    public Integer getStopMemberCnt();
    public Integer getLeaveMemberCnt();

    public Integer getRentalBookCnt();
    public Integer getReturnBookCnt();

    public Date getBookUpdateDate();
    public Date getMemberUpdateDate();
}
