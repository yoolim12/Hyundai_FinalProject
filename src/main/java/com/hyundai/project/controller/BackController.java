package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hyundai.project.mail.MailService;
import com.hyundai.project.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/back")
public class BackController {
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Autowired
	private MailService mail;
	
	// 회원관리 페이지 이동
	@GetMapping("/form")
	public void form(Model model) throws Exception {
		System.out.println(service.showAllMember());
		model.addAttribute("userList", service.showAllMember());
	}
	
	// 회원관리 페이지 이동
	@GetMapping("/test")
	public String st() {
		log.info("회원관리 페이지 요청");
		return "/back/test";
	}
	
	// 상품관리 페이지 이동
	@GetMapping("/form2")
	public String form2() {
		log.info("상품관리 페이지 요청");
		return "/back/form2";
	}
	
	// 매장관리 페이지 이동
	@GetMapping("/form3")
	public String form3() {
		log.info("매장관리 페이지 요청");
		return "/back/form3";
	}
	
	// 차트 페이지 이동
	@GetMapping("/chart")
	public String chart() {
		log.info("상품관리 페이지 요청");
		return "/back/chart";
	}
		
}
