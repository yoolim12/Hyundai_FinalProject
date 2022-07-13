package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.mail.MailService;
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
	
	
	@Autowired
	private MailService mail;

//	@Autowired
//	private MemberDAO memberRepository;

	@RequestMapping("/login")
	public void loginpage() {
		log.info("Login page return");
	}

	@RequestMapping("/loginfail")
	public String loginfail() {
		log.info("Login Fail");
		return "member/login";
	}
	
	@RequestMapping("/simpleRegisterEmail")
	public void simpleRegisterEmail() {
		log.info("simpleRegisterEmail return");
	}
	

	@RequestMapping("/simpleRegister/{memail}")
	public String simpleRegister(Model model, @PathVariable("memail") String memail) {
		MemberDTO dto = new MemberDTO();
		dto.setMemail(memail);
		model.addAttribute("mail", dto);
		return "member/simpleRegister";
	}

	@RequestMapping("/simpleRegister2/{memail}")
	public String simpleRegister2(Model model, @PathVariable("memail") String memail) {
		MemberDTO dto = new MemberDTO();
		dto.setMemail(memail);
		model.addAttribute("mail", dto);
		return "member/simpleRegister2";
	}
	
	@RequestMapping("/simpleRegister3")
	public void simpleRegister3() {
		log.info("simpleRegister3 return");
	}
}//end class