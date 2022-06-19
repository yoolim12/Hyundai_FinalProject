package com.hyundai.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/back")
public class BackMemberRestController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	
	// 회원 전체 조회
	@GetMapping("/allmember")
	@ResponseBody
    public ResponseEntity<List<MemberDTO>> showAllMember(HttpServletResponse response, @RequestParam Map<String, String> map) throws Exception {
		String email = map.get("email");
		System.out.println(email);
		
		ResponseEntity<List<MemberDTO>> mem = null;
		try {
			mem = new ResponseEntity<List<MemberDTO>>(service.showAllMember(), HttpStatus.OK);
			log.info(mem);
		} catch (Exception e) {
			e.printStackTrace();
			mem = new ResponseEntity<List<MemberDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		return mem;
    } // end allmember
	
	// 회원 찾기
	@PostMapping("/findid")
	@ResponseBody
    public ResponseEntity<MemberDTO> findId(HttpServletResponse response, @RequestParam Map<String, String> map) throws Exception {
		String email = map.get("email");
		System.out.println(email);
		
		ResponseEntity<MemberDTO> mem = null;
		try {
			mem = new ResponseEntity<MemberDTO>(service.getMemberInfo(email), HttpStatus.OK);
			log.info(mem);
		} catch (Exception e) {
			e.printStackTrace();
			mem = new ResponseEntity<MemberDTO>(HttpStatus.BAD_REQUEST);
		} // end try
		return mem;
    } // end findid
	
	// 회원정보 수정
    @PostMapping("/modify")
    @ResponseBody
    public void update(HttpServletResponse response, @RequestBody HashMap<String, String> map) throws Exception {
    	System.out.println("modify rr");
    	String email = map.get("memail");
    	String name = map.get("mname");
    	String birth = map.get("birth");
    	String telnum = map.get("telnum");
    	String address = map.get("maddress");
    	
    	System.out.println(name + ' '+ birth + ' ' + telnum + ' ' + address);
    	
    	try {
    		service.modifyMember(email, name, birth, telnum, address);
    		
        	Gson gson = new Gson();
    		response.setContentType("application/json; charset=utf-8");
    		response.getWriter().print(gson.toJson(map)); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    } // end modify
    
    // 회원 탈퇴
    @PostMapping("/delete")
    @ResponseBody
    public void delMember(HttpServletResponse response, @RequestParam Map<String, String> map) throws Exception {
    	String email = map.get("email");
    	
    	System.out.println(email);
    	
    	try {
    		service.delMember(email);
    		
    		Gson gson = new Gson();
    		response.setContentType("application/json; charset=utf-8");
    		response.getWriter().print(gson.toJson(map)); 
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    } // end delete
}