package com.example.fileupload.controller;

import com.example.fileupload.dto.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class V3Controller {

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/v3/upload")
    public String getUpload() {

        return "v3/uploadForm";
    }

    @PostMapping("/v3/upload")
    @ResponseBody // 전송타입이 json이기 때문에 사용
    public List<FileDto> setUpload(@RequestParam("files" )List<MultipartFile> files) throws IOException {
        // for(객체타입 별명 : 이름)

        List<FileDto> list = new ArrayList<>();

        for(MultipartFile mf : files) {
            String oriName = mf.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String ext = oriName.substring(oriName.lastIndexOf("."));

            String savedFileName = uuid + ext;
            String savedPathFileName = fileDir + savedFileName;
            // File url(경로) + 이름
            mf.transferTo(new File(savedPathFileName));
            Long savedFileSize = mf.getSize();

            // 생성자를 이용해서 mapper안에 값을 넣는다
            list.add(new FileDto(oriName, savedFileName, savedPathFileName, savedFileSize ));
        }


        return list;
    }


}
