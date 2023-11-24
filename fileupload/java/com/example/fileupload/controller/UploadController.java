package com.example.fileupload.controller;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
public class UploadController {
        @Value("${fileDir}") //필드
        String fileDir;

        @GetMapping("/upload")
        public String getUpload() {

            return "upload/uploadForm";
        }

        @PostMapping("/upload")
        public String setUpload(Model model, @RequestParam("file") MultipartFile file) throws IOException {
            // @RequestParam("file")생략이 가능하지만 별명을 부여해서 사용하는것이 좋다

            // SimpleDateFormat날짜 형태를 잡는코드 => ex)2023-11-24    20231124
            // new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

            // 저장을 위한 폴더 생성(날짜를 이용해서 생성)
            String folderName = new SimpleDateFormat("yyyyMMdd").format( System.currentTimeMillis() );
            File makefolder = new File(fileDir + folderName);

            // !folderName.exists() 오늘 날짜의 파일을 확인
            if ( !makefolder.exists() ) {
                makefolder.mkdir();
            }

            // 디렉토리 생성
            String oriName = file.getOriginalFilename();
            // 임의의 난수를 생성한다
            String uuid = UUID.randomUUID().toString();
            // .을 기준으로 자른값의 숫자를 반환
            String ext = oriName.substring(oriName.lastIndexOf("."));

            // 이미지 경로 이름만 생성
            String savedFileName = uuid + ext; // 이미지 표시할 때

            // ex) 20231124/ => 오늘 날짜로 생성된 파일 안에 들어가야하기 대문에 "/"를 넣어준다
            String savedPathFileName = fileDir + folderName + "/" + savedFileName; // 파일 다운로드할 때 사용

            // 파일 쓰기 (위치를 넣어준다)
            file.transferTo( new File(savedPathFileName) );

            // view 데이터
            model.addAttribute("savedFileName", savedFileName);
            model.addAttribute("folderName", folderName);
            model.addAttribute("savedPathFileName", savedPathFileName);


            return "upload/uploadView";
        }

        @GetMapping("/uploadView")
        public String getUploadView() {

            return "/upload/uploadView";
        }



        @GetMapping("/download")
        public ResponseEntity<Resource> getDownload(@RequestParam String savedPathFileName) throws MalformedURLException {
            UrlResource resource = new UrlResource("file:" + savedPathFileName);

            // 보낼때는 encode
            String encodedFileName = UriUtils.encode(savedPathFileName, StandardCharsets.UTF_8);
            String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";


            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
        }
}
