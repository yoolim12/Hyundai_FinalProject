package com.hyundai.project.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.ProductBackDTO;
import com.hyundai.project.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/back")
public class BackStreamingRestController {

    @Autowired
    private ProductService productService;


    @PostMapping("/streamingSearch")
	  public ResponseEntity<List<ProductBackDTO>> productSearch(@RequestBody HashMap<String, String> map) {
		String pname = map.get("pname");

		ResponseEntity<List<ProductBackDTO>> mem = null;
		try {
			List<ProductBackDTO> list = productService.productSearch(pname);
			mem = new ResponseEntity<List<ProductBackDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			mem = new ResponseEntity<List<ProductBackDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		  return mem;
	  }

	@GetMapping("/backStreaming/{pid}")
	public ResponseEntity<List<ProductBackDTO>> backProductList(@PathVariable("pid") String pid, Model model) {
		ResponseEntity<List<ProductBackDTO>> product = null;
		try {
			List<ProductBackDTO> list = productService.backProductList(pid);
			model.addAttribute("backProduct", list);
			product = new ResponseEntity<List<ProductBackDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			product = new ResponseEntity<List<ProductBackDTO>>(HttpStatus.BAD_REQUEST);
		}
		return product;
	}
}
