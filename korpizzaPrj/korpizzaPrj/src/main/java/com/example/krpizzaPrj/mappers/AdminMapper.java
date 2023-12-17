package com.example.krpizzaPrj.mappers;


import com.example.krpizzaPrj.dto.UserDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM users ${queryString} ORDER BY user_num DESC LIMIT #{startNum}, #{offset}")
    List<UserDto> getUserAdminPage(String queryString, int startNum, int offset);

    @Select("SELECT COUNT(*) FROM users ${queryString}")
    int getUserAdminCount(String queryString);

    @Select("SELECT * FROM users WHERE user_num = #{userNum}")
    UserDto getViewUser(int id);

    @Delete("DELETE FROM users WHERE user_num = #{userNum}")
    void deleteUser( int id );



}