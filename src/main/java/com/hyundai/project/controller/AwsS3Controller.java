package com.hyundai.project.controller;

import com.hyundai.project.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    /**
     * Amazon S3에 파일 업로드
     * @return 성공 시 200 Success와 함께 업로드 된 파일의 파일명 리스트 반환
     */
    @PostMapping("/file")
    public List<String> uploadFile(@RequestPart List<MultipartFile> multipartFile) {
        return awsS3Service.uploadFile(multipartFile);
    }

    /**
     * Amazon S3에 업로드 된 파일을 삭제
     * @return 성공 시 200 Success
     */
    @DeleteMapping("/file")
    public void deleteFile(@RequestParam String fileName) {
        awsS3Service.deleteFile(fileName);
    }
}