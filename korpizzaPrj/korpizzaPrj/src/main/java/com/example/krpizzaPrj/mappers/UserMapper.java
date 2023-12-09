package com.example.krpizzaPrj.mappers;



import com.example.krpizzaPrj.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

        @Insert("INSERT INTO users VALUES(NULL, #{userId}, #{userPasswd}, #{userEmail}, #{userName}, NOW(), NOW())")
        public void setRegister(UserDto userDto);

        @Select("SELECT COUNT(*) FROM users WHERE user_id = #{userId}")
        public int getCheckUserId(String userId);

        @Select("SELECT COUNT(*) FROM users WHERE user_email = #{userEmail}")
        public int getCheckUserEmail(String userEmail);

        @Select("SELECT users.user_id FROM users WHERE user_name = #{userName} AND user_email = #{userEmail}")
        public UserDto checkFindID( UserDto userDto );

        @Select("SELECT  user_id as userId, user_pw as userPasswd FROM users WHERE  user_id = #{userId} AND user_pw = #{userPasswd}")
        public UserDto checkLogin(UserDto userDto);

    }
