package com.example.member.mappers;

import com.example.member.dto.MemberDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {
    
    // sql문에 값을 넣는 코드   
    @Insert("INSERT INTO member VALUES(NULL, #{userid}, #{passwd}, #{username}, NOW())")
    void setInsert(MemberDto memberDto);
    
    // sql문을 출력하는 코드
    // 이름,아이디를 입력하면 그 값에 맞게 출력
    @Select("SELECT * FROM member ${queryString} ORDER BY id DESC")
    List<MemberDto> getMemberList(String queryString);
    
    // 개수를 출력하는 코드   
    @Select(" SELECT COUNT(*) FROM member ${queryString} ")
    int getMemberCount(String queryString);
    
    
    
    // sql문을 삭제하는 코드
    //  view에서 #{id} id의 번호를 받아 삭제
    @Delete("DELETE FROM member WHERE id= #{id}")
    void deleteMember(int id);
}
