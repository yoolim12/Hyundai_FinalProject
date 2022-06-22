package com.hyundai.project.controller;

import com.hyundai.project.dto.AuthMemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

	@GetMapping("/order")
	public String orderView(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
		model.addAttribute("member", authMemberDTO);
		return "member/ordersheet";
	}

}
