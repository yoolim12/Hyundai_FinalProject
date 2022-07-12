package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.mail.MailService;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

/* 작성자 : 문혁
 * 메인 페이지로 이동시키는 컨트롤러
 */
@Log4j2
@Controller
public class MainController {
	@Autowired
	MailService mail;
	
	@Autowired
	private MemberDAO memberDAO;

	@GetMapping("")
	public String main() throws Exception{
		return "main";
	}
	
	@RequestMapping("/mypageCheck")
	public String mypage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) throws Exception {
		if (oauthMemberDTO == null) {
			model.addAttribute("from_social", memberDAO.findByEmail(authMemberDTO.getMemail(), 0).getFrom_social());
			System.out.println("not social!");
			return "/mypage/personInformationChange";
		}
		else {
			model.addAttribute("from_social", memberDAO.findByEmail(oauthMemberDTO.getMemail(), 1).getFrom_social());
			System.out.println("social!");
			return "/mypage/modifyPage";
		}
	}
}