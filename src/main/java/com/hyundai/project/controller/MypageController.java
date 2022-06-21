package com.hyundai.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/mypage")
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MypageController {	
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/mypage")
	public void mypage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
		model.addAttribute("membername", authMemberDTO.getName());
		model.addAttribute("membergno", memberDAO.findByEmail(authMemberDTO.getEmail(), 0).getGno());
	}
	
	@RequestMapping("/personInformationChange")
	public String mypageModifyPage1(@AuthenticationPrincipal AuthMemberDTO authMemberDTO
			, HttpServletRequest request, Model model) {
		model.addAttribute("memail", authMemberDTO.getEmail());
		System.out.println("1 > " + memberDAO.findByEmail(authMemberDTO.getEmail(), 0).getMpassword());
		System.out.println("2 > " + request.getParameter("j_password"));
		if(request.getParameter("j_password") == memberDAO.findByEmail(authMemberDTO.getEmail(), 0).getMpassword())
			return "/main";
		return "/mypage/modifyPage";
	}
	
//	@RequestMapping("/modifyPage/done")
//	public String mypageModifyPage2(@AuthenticationPrincipal AuthMemberDTO authMemberDTO
//			, HttpServletRequest request) {
//		System.out.println("1 > " + memberDAO.findByEmail(authMemberDTO.getEmail(), 0).getMpassword());
//		System.out.println("2 > " + request.getParameter("j_password"));
//		if(request.getParameter("j_password") == memberDAO.findByEmail(authMemberDTO.getEmail(), 0).getMpassword())
//			return "/main";
//		return "/mypage/modifyPage";
//	}
}
