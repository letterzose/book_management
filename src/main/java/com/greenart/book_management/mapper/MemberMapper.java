package com.greenart.book_management.mapper;

import java.util.List;

import com.greenart.book_management.data.MemberHistoryVO;
import com.greenart.book_management.data.MemberVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void addMemberInfo(MemberVO data);
    public List<MemberVO> getMemberList(String type, String keyword, Integer offset);
    public Integer getMemberCnt(String type, String keyword);

    public void deleteMemberInfo(Integer seq);
    public Integer isExistMember(Integer seq);

    public MemberVO getMemberInfoBySeq(Integer seq);
    public void updateMemberInfo(MemberVO data);

    public void insertMemberHistory(MemberHistoryVO data);
    public Integer getRecentAddedMemberSeq();
}
