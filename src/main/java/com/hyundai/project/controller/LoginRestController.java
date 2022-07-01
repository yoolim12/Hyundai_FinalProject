package com.hyundai.project.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberJoinDTO;
import com.hyundai.project.dto.MemberRoleDTO;
import com.hyundai.project.mail.MailService;
import com.hyundai.project.memberDAO.MemberDAO;
import com.hyundai.project.service.RegisterService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/member")
public class LoginRestController {
	@Autowired
	private RegisterService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MailService mail;
	
	@RequestMapping("/registerPost")
	public void registerPost(@RequestBody HashMap<String, String> map, 
			@ModelAttribute MemberDTO memberdto, @ModelAttribute MemberRoleDTO memberRoleDTO,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Enter registerPost!!");
		String memail = map.get("memail"); // js로 null인 것들 걸러내
		System.out.println("register post memail: " + memail);
		String mpassword = passwordEncoder.encode(map.get("mpassword"));
		String memail_info = map.get("memail_info");
		String mname = map.get("mname");
		Date birth = Date.valueOf(map.get("birth"));
		String telnum = map.get("telnum"); // js로 null인 것들 걸러내
		
		memberdto.setMemail(memail);
		memberRoleDTO.setMemail(memail);
		
		memberdto.setMpassword(mpassword);
		memberdto.setMemail_info(memail_info);
		memberdto.setMname(mname);
		memberdto.setBirth(birth);
		memberdto.setTelnum(telnum);
		memberdto.setFrom_social(0);
		
		memberdto.setMaddress("0");
		memberdto.setMgender("0");
		
		memberdto.setGno(1);
		
		try {
    		service.simpleRegister(memberdto);
    		service.registerRole(memberRoleDTO);
    		
    		mail.welcomeMailSend();
    		System.out.println("register success!!");
    		
        	Gson gson = new Gson();
    		response.setContentType("application/json; charset=utf-8");
    		response.getWriter().print(gson.toJson(map)); 
    	}catch(Exception e){
    		System.out.println("register fail.......");
    		e.printStackTrace();
    	}
	}
	
	@RequestMapping("/duplicateCheck")
//	@ResponseBody
	public ResponseEntity<MemberJoinDTO> duplicateCheck(@RequestBody HashMap<String, String> map, HttpServletResponse response) throws IOException {
		String memail = map.get("memail");
		System.out.println(memail);
		System.out.println("memberDAO.findByEmail(memail, 0): " + memberDAO.findByEmail(memail, 0));
		MemberJoinDTO memberJoinDTO = new MemberJoinDTO();
		memberJoinDTO.setMemail("0");
		ResponseEntity<MemberJoinDTO> mem = null;
		try {
			MemberJoinDTO a = memberDAO.findByEmail(memail, 0);
			if(a == null) {
				mem = new ResponseEntity<MemberJoinDTO>(memberJoinDTO, HttpStatus.OK);
			}
			else {
				mem = new ResponseEntity<MemberJoinDTO>(memberDAO.findByEmail(memail, 0), HttpStatus.OK);
				
			}
			log.info(mem);
		} catch (Exception e) {
			e.printStackTrace();
			mem = new ResponseEntity<MemberJoinDTO>(HttpStatus.BAD_REQUEST);
		} // end try
		return mem;
	}
}
