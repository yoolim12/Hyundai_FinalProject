package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String main() {
		return "/home";
	}// end insert
	
	@GetMapping("/loginForm")
    public String login() {
    	return "loginForm";
    }
}
