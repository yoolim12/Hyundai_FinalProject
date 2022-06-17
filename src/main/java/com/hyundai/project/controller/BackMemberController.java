package com.hyundai.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hyundai.project.dto.ClubMember;
import com.hyundai.project.dto.ClubMemberRoleSet;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/back")
public class BackMemberController {
	
	// 회원관리 페이지 이동
	@GetMapping("/form")
	public String form() {
		log.info("회원관리 페이지 요청");
		return "/back/form";
	}
	
	// 회원 찾기
	@PostMapping("/findid")
    public void findId(HttpServletResponse response, @RequestParam Map<String, Object> map) throws Exception {
    	
    	Gson gson = new Gson();
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(gson.toJson(map)); 	
    } // end findid
	
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
    	//user.setPassword(passwordEncoder.encode(password)); 
    	user.setName(name);
    	
    	//clubR.updateInfo(user);
    	
    	ClubMemberRoleSet user_role = new ClubMemberRoleSet();
    	
    	user_role.setNow(now);
    	user_role.setClub_member_email(email);
    	
    	//clubR.updateRoleEmail(user_role);
        
    	Gson gson = new Gson();
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(gson.toJson(user));
    } // end update
}
