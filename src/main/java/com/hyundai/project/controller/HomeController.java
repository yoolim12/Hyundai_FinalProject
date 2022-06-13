package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/* 
* 작성자 : 문혁, 김승환
* 
* 메인 화면 및 로그인 폼 관련컨트롤러
*/
@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String main() {
		return "home";
	}// end insert
	
	@GetMapping("/loginForm")
    public String login() {
    	return "loginForm";
    }
}
