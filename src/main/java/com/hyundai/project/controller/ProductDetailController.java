package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductDetailController {
	
	@GetMapping("/detail")
	public String productDetail() {
		return "/product/detail";
	}
	
}
