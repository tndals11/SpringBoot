package com.example.upload.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String getUpload() {

        return "upload/upload";
    }


    @PostMapping("/upload")
    public String setUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // 업로드
        // getOriginalFilename() => 내가 업로드한 이름이 찍힌다.
        //  System.out.println(file.getOriginalFilename());
        //  System.out.println(file.getSize());
        // 파일중복 -> 변경(uuid) -> 확장자 기준으로 배열 0 -> uuid 변경하기 -> 저장
        //  UUID 임의의 난수 생성
        //  String uuid = UUID.randomUUID().toString();
        //  System.out.println(uuid);
        String fileDir = "C:/Users/ITPS/Downloads/upload/src/main/java/com/example/upload/";
        String origName = file.getOriginalFilename();

        // .을 기준으로 자르기 => .png
        String ext = origName.substring( origName.lastIndexOf("."));
        // 임의의 난수 생성
        String uuid = UUID.randomUUID().toString();

        String fileName = uuid + ext;
        System.out.println(fileName);

        String savePathFile = fileDir + fileName;

            file.transferTo(new File(savePathFile));

        return "upload/upload";
    }


}
