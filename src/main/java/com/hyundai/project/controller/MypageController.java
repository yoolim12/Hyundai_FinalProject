package com.hyundai.project.controller;

import com.hyundai.project.memberDAO.QnaDAO;
import com.hyundai.project.service.ProductService;
import com.hyundai.project.service.QnaService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/mypage")
public class MypageController {	
	
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private QnaDAO qnaDAO;

	@Setter(onMethod_ = @Autowired)
	private QnaService service;
	
	@RequestMapping
	public String mypage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) throws Exception {
		if (oauthMemberDTO == null) {
      		model.addAttribute("member", authMemberDTO);
      		model.addAttribute("memberemail", authMemberDTO.getMemail());
			model.addAttribute("membername", authMemberDTO.getMname());
			model.addAttribute("memberpoint", memberDAO.getPoint(authMemberDTO.getMemail()));
			log.info("============================point: " + memberDAO.getPoint(authMemberDTO.getMemail()));
			model.addAttribute("membergno", memberDAO.findByEmail(authMemberDTO.getMemail(), 0).getGno());
		}
		else {
      		model.addAttribute("member", oauthMemberDTO);
			model.addAttribute("memberemail", oauthMemberDTO.getMemail());
			model.addAttribute("membername", oauthMemberDTO.getMname());
			model.addAttribute("memberpoint", memberDAO.getPoint(oauthMemberDTO.getMemail()));
			model.addAttribute("membergno", memberDAO.findByEmail(oauthMemberDTO.getMemail(), 1).getGno());
		}
		return "mypage/mypage";
	}
	
	@RequestMapping("/personInformationChange")
	public void mypageModifyPage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		if (oauthMemberDTO == null) {
			model.addAttribute("memail", authMemberDTO.getMemail());
		}
		else {
			model.addAttribute("memail", oauthMemberDTO.getMemail());
		}
	}

	@RequestMapping("/modifyPage")
	public void modifyPage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		
		
		if (oauthMemberDTO == null) {
			model.addAttribute("memail", authMemberDTO.getMemail());
			model.addAttribute("mname",authMemberDTO.getMname());
			model.addAttribute("birth", authMemberDTO.getBirth());
        }
        else {
        	model.addAttribute("memail", oauthMemberDTO.getMemail());
    		model.addAttribute("mname", oauthMemberDTO.getName());
    		model.addAttribute("birth", oauthMemberDTO.getBirth());
        }
	}
	
	@GetMapping("/grade")
	public String gradePage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		
		int grade;
		
		if (oauthMemberDTO == null) {
			model.addAttribute("memail", authMemberDTO.getMemail());
			model.addAttribute("mname",authMemberDTO.getMname());
			model.addAttribute("birth", authMemberDTO.getBirth());
			grade = memberDAO.findByEmail(authMemberDTO.getMemail(), 0).getGno();
        }
        else {
        	model.addAttribute("memail", oauthMemberDTO.getMemail());
    		model.addAttribute("mname", oauthMemberDTO.getName());
    		model.addAttribute("birth", oauthMemberDTO.getBirth());
    		grade = memberDAO.findByEmail(oauthMemberDTO.getMemail(), 0).getGno();
        }
		
		
		String rank = "";
		
		if(grade == 1) {
			rank = "FRIEND";
		}else if (grade == 2) {
			rank = "CREW";
		}else if (grade == 3) {
			rank = "MANIA";
		}else if (grade == 4) {
			rank = "STAR";
		}else {
			rank = "THE STAR";
		}
		model.addAttribute("rank", rank);
		model.addAttribute("grade",grade);
		return "mypage/grade";
	}

	@GetMapping("/qna")
	public String qna(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) throws Exception {
		if (oauthMemberDTO == null) {
			model.addAttribute("member", authMemberDTO);
			model.addAttribute("memberemail", authMemberDTO.getMemail());
			model.addAttribute("membername", authMemberDTO.getMname());
			model.addAttribute("memberpoint", memberDAO.getPoint(authMemberDTO.getMemail()));
			model.addAttribute("membergno", memberDAO.findByEmail(authMemberDTO.getMemail(), 0).getGno());
		}
		else {
			model.addAttribute("member", oauthMemberDTO);
			model.addAttribute("memberemail", oauthMemberDTO.getMemail());
			model.addAttribute("membername", oauthMemberDTO.getName());
			model.addAttribute("memberpoint", memberDAO.getPoint(oauthMemberDTO.getMemail()));
			model.addAttribute("membergno", memberDAO.findByEmail(oauthMemberDTO.getMemail(), 1).getGno());
		}
		return "mypage/qna";
	}

	// QNA 작성 뷰로 이동
	@GetMapping("/insertQna")
	public void insertqna() {
		log.info("Qna 작성페이지 진입");
	}

	// QNA 수정 뷰로 이동
	@GetMapping("/updateQna")
	public void qnaModify(@RequestParam int qid, Model model) throws Exception {
		model.addAttribute("qnaAttribute", service.getQnaDetail(qid));
		log.info(service.getQnaDetail(qid));
		log.info("QNA 수정화면 진입");
	}
}
