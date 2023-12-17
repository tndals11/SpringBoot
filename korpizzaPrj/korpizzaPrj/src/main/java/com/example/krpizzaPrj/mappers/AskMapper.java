package com.example.krpizzaPrj.mappers;

import com.example.krpizzaPrj.dto.AskDto;
import org.apache.ibatis.annotations.Insert;

public interface AskMapper {

    @Insert("INSERT INTO board VALUES(NULL,#{subject},#{writer},#{content}, 0, NOW(), #{orgName}, #{savedFileName}, #{savedFilePathName}, #{savedFileSize}, #{folderName}, #{ext}, #{grp}, 1, 1)")
    void setAsk();

}
