package com.hyundai.project.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRoleDTO;
import com.hyundai.project.memberDAO.MemberDAO;
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
	private MemberDAO memberRepository;
	
//	@Autowired
//	private MemberDTO member;
	
	final String LOGIN_MEMBER = "loginMember";
	
//	@Resource
//	private HandsomeUserDetailsService handsomeUserDetailsService;
	
//	// 권한 상관 없이 모두 접근 가능
//	@PreAuthorize("permitAll()")
	@RequestMapping("/login")
//	@PostMapping("/HandsomeLogin/HandsomeLoginpage")
	public void loginpage() {
		System.out.println("Login page return");
	}
	
//	@RequestMapping("/loginsuccess")
//	@PostMapping("/HandsomeLogin/loginsuccess")
//	public String loginSuccess(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, HttpServletRequest request, Model model) {
////		MemberJoinDTO loginMember = memberRepository.findByEmail(member.getMemail(),0);
//		MemberJoinDTO loginMember = memberRepository.findByEmail("yoolo0212@gmail.com",0);
//		AuthMemberDTO loginMember = authMemberDTO;
//		System.out.println("nvjnqbvobeowbvoebjesdbeiwvbdfhsibhfyvbasifvyiehwbvd: " + authMemberDTO.getEmail());
//		// 로그인 성공 처리
//        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성해서 반환
//        // getSession() : 디폴트가 true, false는 세션이 없을 때 새로 만들지 않고 null을 반환
//        HttpSession session = request.getSession();
//     // 세션에 로그인 회원 정보 보관
//        session.setAttribute(LOGIN_MEMBER, loginMember);
//        System.out.println("session.getAttribute(LOGIN_MEMBER): "+session.getAttribute(LOGIN_MEMBER));
//        return "main";
//	}
	
	@RequestMapping("/loginfail")
//	@PostMapping("/HandsomeLogin/loginfail")
	public String loginfail(Model model) {
		System.out.println("Login Fail");
//		model.addAttribute("msg","이메일 주소 또는 비밀번호가 올바르지 않습니다.");
		return "/member/login";
	}
	
//	https://always-develop.tistory.com/58
//	@RequestMapping(value="/HandsomeLoginpage", method=RequestMethod.POST)
//	public void loginDone(@AuthenticationPrincipal AuthMemberDTO authMemberDTO
//			, HttpServletRequest request) {
//		System.out.println("login done return");
////		String memail = request.getParameter("memail");
////		String mpassword = request.getParameter("mpassword");
////		UserDetails userDetails = handsomeUserDetailsService.loadUserByUsername(memail);
//		
////		return "main";
//	}
	
//	@PreAuthorize("permitAll()")
	@RequestMapping("/simpleRegister")
	public void simpleRegister() {
		System.out.println("simpleRegister return");
	}
	
//	@PreAuthorize("permitAll()")
	@RequestMapping("/simpleRegister2")
	public void simpleRegister2() {
		System.out.println("simpleRegister2 return");
	}
	
//	@PreAuthorize("permitAll()")
	@RequestMapping("/simpleRegister2/done")
	public String simpleRegisterDone(@ModelAttribute MemberDTO memberdto,
			@ModelAttribute MemberRoleDTO memberRoleDTO,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
			System.out.println("Enter simpleRegister2 DONE");
			
			if(request.getParameter("memail1") == null || request.getParameter("memail2") == null) {
				memberdto.setMemail(null);
			}
			else {
				String memail = request.getParameter("memail1") + "@" + request.getParameter("memail2");
				memberdto.setMemail(memail);
				memberRoleDTO.setMemail(memail);
				System.out.println("******************MEMAIL*****************" + memail);
			}
			
			
			
			memberdto.setMpassword(passwordEncoder.encode(request.getParameter("mpassword")));
//			memberdto.setMpassword(request.getParameter("mpassword"));
			
			String memail_info = request.getParameter("memail_info1") + "@" + request.getParameter("memail_info2");
			memberdto.setMemail_info(memail_info);
			System.out.println("******************MEMAIL_INFO1*****************" + request.getParameter("memail_info1"));
			
			
			memberdto.setMname(request.getParameter("mname"));
			
			String b = request.getParameter("myear") + "-" 
			+ request.getParameter("mmonth") + "-" + request.getParameter("mday");
			System.out.println("******************YEAR*****************" + request.getParameter("myear"));
			System.out.println("******************MONTH*****************" + request.getParameter("mmonth"));
			System.out.println("******************DAY*****************" + request.getParameter("mday"));
			
			System.out.println("******************B*****************" + b);
			
			Date birth = Date.valueOf(b);
			System.out.println("******************BIRTH*****************" + birth.toString());
			
			memberdto.setBirth(birth);

			System.out.println("yooooooooooooooooolim"+memberdto);
			
			service.simpleRegister(memberdto);
			
			service.registerRole(memberRoleDTO);
	       
	       return "/member/login";
	   }//end ex..
}//end class