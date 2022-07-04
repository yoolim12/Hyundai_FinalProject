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
	
	@RequestMapping("/simpleRegisterEmail")
	public void simpleRegisterEmail() {
		System.out.println("simpleRegisterEmail return");
	}
	
	@RequestMapping("/emailSend")
	public void emailSend() {
		
	}

	@RequestMapping("/simpleRegister")
	public void simpleRegister() {
		System.out.println("simpleRegister return");
	}

	@RequestMapping("/simpleRegister2")
	public void simpleRegister2() {
		System.out.println("simpleRegister2 return");
	}
	
	@RequestMapping("/simpleRegister3")
	public void simpleRegister3() {
		System.out.println("simpleRegister3 return");
	}
}//end class