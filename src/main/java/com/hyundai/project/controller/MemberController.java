package com.hyundai.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.dto.ClubMember;
import com.hyundai.project.dto.ClubMember2;
import com.hyundai.project.dto.ClubMemberRole;
import com.hyundai.project.dto.ClubMemberRoleSet;
import com.hyundai.project.service.MemberService;

import lombok.extern.log4j.Log4j2;
/* 
* 작성자 : 김승환
* 
* 회원관리와 관련된 기능을 담당하는 컨트롤러
* 회원가입, 회원정보 수정, 회원탈퇴, 관리자 페이지, 아이디 중복
*/
@Controller
@Log4j2
@RequestMapping("/member/")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MemberController {
	
	// 패스워드 암호화
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private MemberService clubR;
    
    
    // 아이디 중복 확인
    @PostMapping("/checkid")
    public void checkId(HttpServletResponse response, @RequestParam Map<String, Object> map) throws Exception {
    	String email = (String) map.get("email");
    	
    	ClubMember2 user2 = new ClubMember2();
    	user2 = clubR.findByEmail(email, 0);
    	if(user2 == null) {
    		user2 = new ClubMember2();
    	}
    	String get_email = user2.getEmail();
    	
    	user2.setEmail(get_email);
        user2.setFrom_social(0);
        
    	Gson gson = new Gson();
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(gson.toJson(user2)); 
    } // end checkId
    
    // 회원 가입 폼 이동
    @GetMapping("/joinForm")
    public String joinForm() {
    	return "member/JoinForm";
    } // end joinForm
    
    // 회원 가입
	@PreAuthorize("permitAll()")
	@PostMapping("/join")
	public String join(ClubMember user) throws Exception {
		log.info("join.....");
		//이메일
		String email = user.getEmail();
		user.setEmail(email);
		
		//비밀번호
		String pwd = user.getPassword();
		String encPwd = passwordEncoder.encode(pwd);
		user.setPassword(encPwd);
		
		//이름
		String name = user.getName();
		user.setName(name);
		
		//소셜 여부
		int from_social = 0;
		user.setFrom_social(from_social);
		
		clubR.insertClubMember(user);
		
		// Role 설정
		ClubMemberRoleSet clubMemberRoleSet = new ClubMemberRoleSet();  
		
		clubMemberRoleSet.setClub_member_email(email);
		clubMemberRoleSet.setRole_set(ClubMemberRole.USER.toString());
		
		clubR.insertClubRoleSet(clubMemberRoleSet);
		return "home";
	}// end join
	
	// 회원정보 수정 (post update)
    @PostMapping("/update")
    @ResponseBody
    public void update(HttpServletResponse response, @RequestBody HashMap<String, String> map) throws Exception {
    	String now = map.get("now");
    	String email = map.get("email");
    	String password = map.get("password");
    	String name = map.get("name");
    	
    	ClubMember user = new ClubMember();
    	
    	user.setNow(now);
    	user.setEmail(email);
    	user.setPassword(passwordEncoder.encode(password));
    	user.setName(name);
    	
    	clubR.updateInfo(user);
    	
    	ClubMemberRoleSet user_role = new ClubMemberRoleSet();
    	
    	user_role.setNow(now);
    	user_role.setClub_member_email(email);
    	
    	clubR.updateRoleEmail(user_role);
        
    	Gson gson = new Gson();
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(gson.toJson(user));
    } // end update
    
    // 회원정보 삭제 (get update)
    @GetMapping("/update")	
    public String delete(@RequestParam String email) throws Exception {
    	
    	clubR.deleteRoleSet(email);
    	clubR.deleteInfo(email);
    	
    	return "/home";
    } // end delete
    	
	// 관리자 페이지
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public void exAdmin(Model model) throws Exception {
		log.info("exAdmin.....");
		model.addAttribute("userList", clubR.findAll());
	}// end exAdmin
	
	// 회원 정보 반환
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/modify")
	public void exmodify(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO, Model model) {
		log.info("exModify.....");
		log.info("--------------");
		log.info(clubAuthMemberDTO);
		model.addAttribute("user", clubAuthMemberDTO);
	}// end exmodify
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/member")
	public String exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO) {
		log.info("exMember.....");
		log.info(clubAuthMemberDTO);
		return "/home";
	}// end exMember
}// end class
