package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/back")
public class BackController {
	// 회원관리 페이지 이동
		@GetMapping("/form")
		public String form() {
			log.info("회원관리 페이지 요청");
			return "/back/form";
	}
}
