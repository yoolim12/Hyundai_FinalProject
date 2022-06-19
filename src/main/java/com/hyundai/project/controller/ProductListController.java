package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.PageDTO;
import com.hyundai.project.service.ProductListService;


@Controller
public class ProductListController {
	@Autowired
	private ProductListService service;
	
	@GetMapping("/list/{clarge}/{cmedium}/{csmall}")
	public String productList(Criteria cri, Model model, @PathVariable("clarge") String clarge, @PathVariable("cmedium") String cmedium, @PathVariable("csmall") String csmall) throws Exception {
//		log.info("상품 리스트 페이지 요청");
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(clarge, cmedium, csmall)));
		System.out.println(cri);
		System.out.println(service.getTotal(clarge, cmedium, csmall));
		return "/product/list";
	}
}