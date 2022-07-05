package com.hyundai.project.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.MemberJoinDTO;
import com.hyundai.project.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/back")
public class BackMemberRestController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	// 회원 찾기
	@PostMapping("/member")
	@ResponseBody
    public ResponseEntity<List<MemberJoinDTO>> findId(HttpServletResponse response, @RequestParam Map<String, String> map) throws Exception {
		String email = map.get("email");
		System.out.println(email);
		
		ResponseEntity<List<MemberJoinDTO>> mem = null;
		try {
			mem = new ResponseEntity<List<MemberJoinDTO>>(service.getMemberInfo(email), HttpStatus.OK);
			//log.info(mem);
		} catch (Exception e) {
			e.printStackTrace();
			mem = new ResponseEntity<List<MemberJoinDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		return mem;
    } // end findid
	
	// 회원정보 수정
    @PutMapping("/member")
    @ResponseBody
    public String update(HttpServletResponse response, @RequestBody HashMap<String, String> map) throws Exception {
    	System.out.println("modify rr");
    	String email = map.get("memail");
    	String name = map.get("mname");
//    	String birth = map.get("birth");
    	Date birth = Date.valueOf(map.get("birth"));
    	String telnum = map.get("telnum");
    	String address = map.get("maddress");
    	int gno = Integer.parseInt(map.get("gno"));
    	int auth = Integer.parseInt(map.get("auth"));
    	int msleep = Integer.parseInt(map.get("msleep"));
    	
    	System.out.println(name + ' '+ birth + ' ' + telnum + ' ' + address + ' ' + msleep);
    	
    	try {
    		service.admodifyMember(email, name, birth, telnum, address, gno, msleep);
    		
    		if(auth == 1)
    			service.admodifyAuth(email, "USER");
    		else if(auth == 2)
    			service.admodifyAuth(email, "EMPLOYEE");
    		else if(auth == 3)
    			service.admodifyAuth(email, "ADMIN");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "success";
    } // end modify
    
    // 회원 탈퇴
    @DeleteMapping("/member")
    @ResponseBody
    public String delMember(HttpServletResponse response, @RequestBody HashMap<String, String> map) throws Exception {
    	String email = map.get("email");
    	
    	System.out.println(email+"test");
    	
    	try {
            log.info(email);
            service.delMember(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    } // end delete
    
    // 회원정보 수정
    @PutMapping("/grade")
    @ResponseBody
    public String updateGrade(HttpServletResponse response, @RequestBody HashMap<String, String> map) throws Exception {
    	System.out.println("modify rr");
    	String email = map.get("memail");
    	String name = map.get("mname");
//    	String birth = map.get("birth");
    	Date birth = Date.valueOf(map.get("birth"));
    	String telnum = map.get("telnum");
    	String address = map.get("maddress");
    	int gno = Integer.parseInt(map.get("gno"));
//    	int auth = Integer.parseInt(map.get("auth"));
    	int msleep = Integer.parseInt(map.get("msleep"));
    	
    	System.out.println(name + ' '+ birth + ' ' + telnum + ' ' + address);
    	
    	try {
    		service.admodifyMember(email, name, birth, telnum, address, gno, msleep);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "success";
    } // end modify
}
