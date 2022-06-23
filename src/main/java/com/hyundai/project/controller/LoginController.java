package com.hyundai.project.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRoleDTO;
import com.hyundai.project.service.RegisterService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member")
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class LoginController {

	@Autowired
	private RegisterService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private MemberDAO memberRepository;

	@RequestMapping("/login")
	public void loginpage() {
		System.out.println("Login page return");
	}

	@RequestMapping("/loginfail")
	public String loginfail() {
		System.out.println("Login Fail");
		return "/member/login";
	}

	@RequestMapping("/simpleRegister")
	public void simpleRegister() {
		System.out.println("simpleRegister return");
	}

	@RequestMapping("/simpleRegister2")
	public void simpleRegister2() {
		System.out.println("simpleRegister2 return");
	}

//	@RequestMapping(value="/simpleRegister2/done")
//	public String simpleRegisterDone(@ModelAttribute MemberDTO memberdto,
//			@ModelAttribute MemberRoleDTO memberRoleDTO,
//			HttpServletResponse response, HttpServletRequest request) throws Exception {
//			System.out.println("Enter simpleRegister2 DONE");
//
//			String memail = request.getParameter("memail1") + "@" + request.getParameter("memail2");
//				memberdto.setMemail(memail);
//				memberRoleDTO.setMemail(memail);
//
//			memberdto.setMpassword(passwordEncoder.encode(request.getParameter("mpassword")));
//
//			String memail_info = request.getParameter("memail_info1") + "@" + request.getParameter("memail_info2");
//			memberdto.setMemail_info(memail_info);
//
//			memberdto.setMname(request.getParameter("mname"));
//
//			String b = request.getParameter("myear") + "-"
//			+ request.getParameter("mmonth") + "-" + request.getParameter("mday");
//
//			Date birth = Date.valueOf(b);
//
//			memberdto.setBirth(birth);
//
//			memberdto.setTelnum("0");
//			memberdto.setMaddress("0");
//			memberdto.setMgender("0");
//			memberdto.setMemail_info("0");
//
//			service.simpleRegister(memberdto);
//
//			service.registerRole(memberRoleDTO);
//
//	       return "/member/login";
//	   }
}//end class