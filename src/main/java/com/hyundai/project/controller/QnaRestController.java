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
        log.info("INSERT QNA : " + qna);
        String memail;
        if (oauthMemberDTO == null) {
            memail = authMemberDTO.getMemail();
        } else {
            memail = oauthMemberDTO.getMemail();
        }
        log.info(qimage);
        if (qimage != null) {
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

    // ajax로 작성된 QNA 리스트를 받아오는 메소드 (페이징)
    @GetMapping("/QNAList")
    @ResponseBody
    public List<QnaDTO> qnaListGet(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO) throws Exception {
        String memail;
        if (oauthMemberDTO == null) {
            memail = authMemberDTO.getMemail();
        } else {
            memail = oauthMemberDTO.getMemail();
        }
        log.info("QNA 리스트 ");
        List<QnaDTO> qnaList = service.getQna(memail); // 로그인된 사용자와 현재 페이지, 페이지에 보여질 게시물 개수를 전달
        log.info(qnaList);
        return qnaList; // 리스트 반환
    }

    // QNA 삭제 기능
    @DeleteMapping("/deleteQNA")
    public void qnaDelete(@RequestParam int qid) throws Exception {
        log.info("QNA 삭제" + qid);
        service.deleteQna(qid); // qid를 받아와 해당 게시물 삭제
    }

    @PutMapping("/qna")
    public void updateProduct(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, @RequestPart(value = "key") QnaDTO qna, @RequestPart(value = "qimage", required = false) MultipartFile qimage) throws Exception {
        log.info("UPDATE QNA : " + qna);
        String memail;
        if (oauthMemberDTO == null) {
            memail = authMemberDTO.getMemail();
        } else {
            memail = oauthMemberDTO.getMemail();
        }
        log.info(qimage);
        if (qimage != null) {
            // S3 이미지 업로드 (Ccolorimage)
            List<MultipartFile> temp = new ArrayList<>();
            temp.add(qimage);
            List<String> q = awsS3Service.uploadFile(temp);
            log.info("S3" + q.get(0));
            qna.setQimage(q.get(0));
        } else qna.setQimage(qna.getQimage());
        qna.setMemail(memail);
        log.info(qna);

        service.updateQna(qna);
    }

    @GetMapping("/qnaDetail")
    @ResponseBody
    public QnaDTO qnaGetDetail(@RequestParam int qid) throws Exception {
        log.info("QNA 디테일 ");
        QnaDTO qna = service.getQnaDetail(qid); // 로그인된 사용자와 현재 페이지, 페이지에 보여질 게시물 개수를 전달
        log.info(qna);
        return qna;
    }

} // end class