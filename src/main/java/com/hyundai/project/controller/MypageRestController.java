package com.hyundai.project.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/mypage")
public class MypageRestController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping(value="/passwordCheck", method=RequestMethod.POST)
	@ResponseBody
	public void passwordCheck(@RequestBody HashMap<String, String> map, 
			@AuthenticationPrincipal AuthMemberDTO authMemberDTO, HttpServletResponse response) throws IOException {
		String mpassword = map.get("mpassword");

		if(passwordEncoder.matches(mpassword, memberDAO.findByEmail(authMemberDTO.getEmail(), 0).getMpassword())) {
			Gson gson = new Gson();
    		response.setContentType("application/json; charset=utf-8");
    		response.getWriter().print(gson.toJson(map));
			System.out.println("success!!");
		}
		else {
			response.getWriter().print(false);
			System.out.println("fail!!");
		}
	}
}
