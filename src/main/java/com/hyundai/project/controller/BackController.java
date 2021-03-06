package com.hyundai.project.controller;

import java.util.Map;

import com.hyundai.project.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@Setter(onMethod_ = @Autowired)
	private QnaService qservice;
	
	@Autowired
	private MailService mail;
	
	// 회원관리 페이지 이동
	@GetMapping("/memberBO")
	public void memberBO(Model model) throws Exception {
		model.addAttribute("userList", service.showAllMember());
		model.addAttribute("userTotal", service.getTotalMember());
		model.addAttribute("amountTotal", service.getTotalAmount());
		model.addAttribute("amountWeek", service.getOrderAmountOfWeek());
		model.addAttribute("revenueTotal", service.getTotalRevenue());
	}
	
	// 회원 조회 ajax + thymleaf
	@PostMapping("/view")
	public String view(Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		String memail = paramMap.get("email");
		log.info(service.getMemberInfo(memail));
		model.addAttribute("userList", service.getMemberInfo(memail));
		
		return "back/memberBO :: #memberInfo";
	}
	
	// 임직원 조회 ajax + thymleaf
	@PostMapping("/viewEmployee")
	public String viewEmployee(Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		String memail = paramMap.get("email");
		log.info(service.getEmployeeInfo(memail));
		model.addAttribute("userList", service.getEmployeeInfo(memail));
		
		return "back/memberBO :: #employeeInfo";
	}
	
	// 임직원 검색 조회 ajax + thymleaf
	@PostMapping("/SfindEmployee")
	public String SfindEmployee(Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		String memail = paramMap.get("email");
		log.info(service.SfindEmployee(memail));
		model.addAttribute("userList", service.SfindEmployee(memail));
			
		return "back/memberBO :: #employeeInfo";
	}
	
	// 스트리밍 페이지 이동
	@GetMapping("/test")
	public String st() {
		log.info("스트리밍 페이지 요청");
		return "back/test";
	}
		
	// 스트리밍 페이지 이동
	@GetMapping("/test2")
	public String st2() {
		log.info("스트리밍 페이지 요청");
		return "back/test2";
	}
	
	// 매장관리 페이지 이동
	@GetMapping("/productBO")
	public String productBO() {
		log.info("매장관리 페이지 요청");
		return "back/productBO";
	}
	
	// 차트 페이지 이동
	@GetMapping("/chart")
	public String chart() {
		log.info("상품관리 페이지 요청");
		return "back/chart";
	}
	
	// 스트리밍 페이지 이동
	@GetMapping("/streamingBO")
	public String streamingBO() {
		log.info("스트리밍 페이지 요청");
		return "back/streamingBO";
	}
	
	// 이메일 보내기 페이지 이동
	@GetMapping("/sendMail")
	public String sendEmail() {
		log.info("이메일 페이지 요청");
		return "back/sendMail";
	}

	// QNA BO 페이지 이동
	@GetMapping("/qnaBO")
	public void qnaBO(Model model) throws Exception {
		model.addAttribute("qnaList", qservice.getAllQna());
	}

	// QNA 조회 ajax + thymleaf
	@PostMapping("/qnaView")
	public String qnaView(Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		String memail = paramMap.get("email");
		log.info(memail);
		model.addAttribute("qnaList", qservice.getQnaInfo(memail));

		return "back/qnaBO :: #qnaInfo";
	}
		
}
