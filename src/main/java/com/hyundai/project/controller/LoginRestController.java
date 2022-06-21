//package com.hyundai.project.controller;
//
//import java.sql.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hyundai.project.dto.MemberDTO;
//import com.hyundai.project.dto.MemberRoleDTO;
//import com.hyundai.project.memberDAO.MemberDAO;
//import com.hyundai.project.service.RegisterService;
//
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@RestController
//@RequestMapping("/member")
//public class LoginRestController {
//	@Autowired
//	private RegisterService service;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private MemberDAO memberRepository;
//	
//	@RequestMapping(value="/simpleRegister2/done", method=RequestMethod.POST)
//	public void simpleRegisterDone(@ModelAttribute MemberDTO memberdto,
//			@ModelAttribute MemberRoleDTO memberRoleDTO,
//			HttpServletResponse response, HttpServletRequest request) throws Exception {
//			System.out.println("Enter simpleRegister2 DONE");
//			
//			if(request.getParameter("memail1") == null || request.getParameter("memail2") == null) {
//				memberdto.setMemail(null);
//			}
//			else {
//				String memail = request.getParameter("memail1") + "@" + request.getParameter("memail2");
//				memberdto.setMemail(memail);
//				memberRoleDTO.setMemail(memail);
//			}
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
//			service.simpleRegister(memberdto);
//			
//			service.registerRole(memberRoleDTO);
//	       
////	       return "/member/login";
//	   }//end ex..
//}
