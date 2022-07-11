package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailController {

	@GetMapping("/detail/{pid}")
	public String productDetail(@PathVariable("pid") String pid) {
		return "product/detail";
	}

}
