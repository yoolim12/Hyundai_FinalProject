package com.hyundai.project.controller;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@Controller
public class OrderController {

	@GetMapping("/order")
	public String orderView( @AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		log.info(authMemberDTO);
		if (oauthMemberDTO == null) {
			model.addAttribute("member", authMemberDTO);
		}
		else {
			model.addAttribute("member", oauthMemberDTO);
		}
		return "member/ordersheet";
	}

}
