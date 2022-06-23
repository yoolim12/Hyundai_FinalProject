package com.hyundai.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.OrderResDTO;
import com.hyundai.project.memberDAO.MemberDAO;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.service.OrderService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/mypage")
public class MypageRestController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private OrderService oservice;
	
	@RequestMapping(value="/passwordCheck", method=RequestMethod.POST)
	@ResponseBody
	public void passwordCheck(@RequestBody HashMap<String, String> map, 
			@AuthenticationPrincipal AuthMemberDTO authMemberDTO, HttpServletResponse response) throws IOException {
		String mpassword = map.get("mpassword");

		if(passwordEncoder.matches(mpassword, memberDAO.findByEmail(authMemberDTO.getMemail(), 0).getMpassword())) {
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
	
	@RequestMapping(value="/passwordCompare")
	@ResponseBody
	public void passwordCompare(@RequestBody HashMap<String, String> map, 
			@AuthenticationPrincipal AuthMemberDTO authMemberDTO, HttpServletResponse response) throws IOException {
		String oldpasswordinput = map.get("oldpassword");
		if(passwordEncoder.matches(oldpasswordinput, memberDAO.findByEmail(authMemberDTO.getMemail(), 0).getMpassword())) {
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
	
	@RequestMapping(value="/modifyPassword")
	@ResponseBody
	public void modifyPassword(@RequestBody HashMap<String, String> map, 
			@AuthenticationPrincipal AuthMemberDTO authMemberDTO, HttpServletResponse response) {
		
		String mpassword = passwordEncoder.encode(map.get("mpassword"));
//		MemberJoinDTO memberJoinDTO = memberDAO.findByEmail(authMemberDTO.getEmail(), 0);
//		Date birth = memberJoinDTO.getBirth();
		authMemberDTO.setMpassword(mpassword);
		
		System.out.println("##############AUTHMEMBER###############"+authMemberDTO);
		
		try {
    		service.modifyMember(authMemberDTO);
    		
        	Gson gson = new Gson();
    		response.setContentType("application/json; charset=utf-8");
    		response.getWriter().print(gson.toJson(map)); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	@GetMapping("/{memail}")
	public ResponseEntity<List<OrderResDTO>> getCart(@PathVariable("memail") String memail) {
        ResponseEntity<List<OrderResDTO>> entry = null;
        
        try {
            entry = new ResponseEntity<List<OrderResDTO>>(oservice.getOrder(memail), HttpStatus.OK);
            log.info(entry);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<List<OrderResDTO>>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
	}
}
