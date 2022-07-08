package com.hyundai.project.controller;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class StreamingController {

	@GetMapping("/streaming")
	public void stream(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {

		log.info("@StreamingController, GET()");
		if (oauthMemberDTO == null)
			model.addAttribute("member", authMemberDTO);
		else
			model.addAttribute("member", oauthMemberDTO);

	}
	
	@GetMapping("/mobile")
	public void mobilestream(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		log.info("@StreamingMobileController, GET()");
		if (oauthMemberDTO == null)
			model.addAttribute("member", authMemberDTO);
		else
			model.addAttribute("member", oauthMemberDTO);
	}
}
