package com.example.krpizzaPrj.mappers;



import com.example.krpizzaPrj.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
        
        // 회원가입
        @Insert("INSERT INTO users VALUES(NULL, #{userId}, #{userPasswd}, #{userEmail}, #{userName}, NOW(), NOW())")
        public void setRegister(UserDto userDto);
        
        // 아이디 중복 체크
        @Select("SELECT COUNT(*) FROM users WHERE user_id = #{userId}")
        public int getCheckUserId(String userId);
        
        // 이메일 중복 체크
        @Select("SELECT COUNT(*) FROM users WHERE user_email = #{userEmail}")
        public int getCheckUserEmail(String userEmail);

        // 아이디 찾기
        @Select("SELECT users.user_id FROM users WHERE user_name = #{userName} AND user_email = #{userEmail}")
        public UserDto checkFindID( UserDto userDto );

        // 비밀번호 찾기
        // #userPasswd# userPasswd가 없어서 오류 계속 발생!! mybatis 이름 변환이 달라서 그런것 같다
        @Select("SELECT users.user_passwd  FROM users WHERE user_id = #{userId} AND user_email = #{userEmail}")
        public UserDto checkFindPw( UserDto userDto);

        // 로그인
        @Select("SELECT * FROM users  WHERE user_id = #{userId} AND user_passwd = #{userPasswd}")
        public UserDto checkLogin(UserDto userDto);


        @Select("SELECT COUNT(*) FROM users  WHERE user_email = #{user_Email} AND user_num = #{userNum}")
        public int setGoupDatePage(String userEmail, int userNum);

    }
