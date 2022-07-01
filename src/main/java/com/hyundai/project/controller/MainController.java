package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hyundai.project.mail.MailService;

import lombok.extern.log4j.Log4j2;

/* 작성자 : 문혁
 * 메인 페이지로 이동시키는 컨트롤러
 */
@Log4j2
@Controller
public class MainController {
	@GetMapping("/main")
	public void main() {
		log.info("메인페이지 요청");
	}
}