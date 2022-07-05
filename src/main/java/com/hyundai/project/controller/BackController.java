package com.hyundai.project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.mail.MailService;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.service.ProductService;

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
		//System.out.println(service.showAllMember());
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
		System.out.println(memail);
		System.out.println(service.getMemberInfo(memail));
		model.addAttribute("userList", service.getMemberInfo(memail));
		
		return "/back/form :: #memberInfo";
	}
	
	// 임직원 조회 ajax + thymleaf
	@PostMapping("/viewEmployee")
	public String viewEmployee(Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		String memail = paramMap.get("email");
		System.out.println(memail);
		System.out.println(service.getEmployeeInfo(memail));
		model.addAttribute("userList", service.getEmployeeInfo(memail));
		
		return "/back/form :: #employeeInfo";
	}
	
	// 임직원 검색 조회 ajax + thymleaf
	@PostMapping("/SfindEmployee")
	public String SfindEmployee(Model model, @RequestParam Map<String, String> paramMap) throws Exception {
		String memail = paramMap.get("email");
		System.out.println(memail);
		System.out.println(service.SfindEmployee(memail));
		model.addAttribute("userList", service.SfindEmployee(memail));
			
		return "/back/form :: #employeeInfo";
	}
	
	// 스트리밍 페이지 이동
	@GetMapping("/test")
	public String st() {
		log.info("스트리밍 페이지 요청");
		return "/back/test";
	}
		
	// 스트리밍 페이지 이동
	@GetMapping("/test2")
	public String st2() {
		log.info("스트리밍 페이지 요청");
		return "/back/test2";
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
