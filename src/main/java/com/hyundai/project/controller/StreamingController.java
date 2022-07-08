package com.hyundai.project.controller;

import com.hyundai.project.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class StreamingController {

	@GetMapping("/streaming")
	public void stream(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model){

		log.info("@StreamingController, GET()");
		model.addAttribute("member", authMemberDTO);

	}
	
	@GetMapping("/mobile")
	public void mobilestream(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model){
		log.info("@StreamingController, GET()");
		model.addAttribute("member", authMemberDTO);
	}
}
