package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ProductListController {
	
	@GetMapping("/list")
	public String productList() {
		log.info("상품 리스트 페이지 요청");
		return "/product/list";
	}
}
