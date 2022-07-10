package com.hyundai.project.controller;

import com.hyundai.project.dto.*;
import com.hyundai.project.service.AwsS3Service;
import com.hyundai.project.service.CartService;
import com.hyundai.project.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequestMapping("/mypage")
@RequiredArgsConstructor
@RestController
public class QnaRestController {
    @Autowired
    private QnaService service;

    private final AwsS3Service awsS3Service;

    @PostMapping("/qna")
    public void insertProduct(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, @RequestPart(value = "key") QnaDTO qna, @RequestPart(value = "qimage", required = false) MultipartFile qimage) throws Exception {
        log.info("INSERT PRODUCT : " + qna);
        String memail;
        if (oauthMemberDTO == null) {
            memail = authMemberDTO.getMemail();
        }
        else {
            memail = oauthMemberDTO.getMemail();
        }
        log.info(qimage);
        if(qimage != null) {
            // S3 이미지 업로드 (Ccolorimage)
            List<MultipartFile> temp = new ArrayList<>();
            temp.add(qimage);
            List<String> q = awsS3Service.uploadFile(temp);
            qna.setQimage(q.get(0));
        } else qna.setQimage("");
        qna.setMemail(memail);
        log.info(qna);

        service.insertQna(qna);
    }

//    @DeleteMapping
//    public String deleteCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, @RequestBody CartDTO cart) throws Exception {
//        String memail;
//        try {
//            if (oauthMemberDTO == null) {
//                memail = authMemberDTO.getMemail();
//            }
//            else {
//                memail = oauthMemberDTO.getMemail();
//            }
//            log.info(memail, cart);
//            service.deleteCart(memail, cart);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "success";
//    }
//
//    @DeleteMapping("/all")
//    public String deleteALLCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO) throws Exception {
//        String memail;
//        try {
//            if (oauthMemberDTO == null) {
//                memail = authMemberDTO.getMemail();
//            }
//            else {
//                memail = oauthMemberDTO.getMemail();
//            }
//            service.deleteAllCart(memail);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "success";
//    }
//
//    @PutMapping
//    public String updateCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, @RequestBody CartUpdateDTO cart) {
//        String memail;
//        try {
//            if (oauthMemberDTO == null) {
//                memail = authMemberDTO.getMemail();
//            }
//            else {
//                memail = oauthMemberDTO.getMemail();
//            }
//            log.info(cart);
//            service.updateCart(memail, cart);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "success";
//    }
//
//    @PostMapping("/station")
//    public OrderStationDTO orderStation(@RequestBody OrderStationDTO station) {
//        return station;
//    }
//
//    @GetMapping("/total")
//    public int getCartTotal(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO) throws Exception {
//        String memail;
//        if(authMemberDTO == null && oauthMemberDTO == null) return 0;
//        if (oauthMemberDTO == null) {
//            memail = authMemberDTO.getMemail();
//        }
//        else {
//            memail = oauthMemberDTO.getMemail();
//        }
//        return service.getCartTotal(memail);
//    }

} // end class