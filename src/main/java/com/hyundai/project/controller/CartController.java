package com.hyundai.project.controller;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

	@GetMapping("/cart")
	public String productDetail(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		if (oauthMemberDTO == null) {
			model.addAttribute("memberEmail", authMemberDTO.getMemail());
		}
		else {
			model.addAttribute("memberEmail", oauthMemberDTO.getEmail());
		}
		return "/member/cart";
	}
}
