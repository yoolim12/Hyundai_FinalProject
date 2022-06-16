package com.hyundai.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.PageVO;
import com.hyundai.project.dto.ProductListDTO;
import com.hyundai.project.service.ProductListService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/product/*")
@RestController
public class ProductListRestController {
	@Autowired
	private ProductListService service;
	
	@GetMapping(value = "/productList")
	public ResponseEntity<List<ProductListDTO>> list() {
		ResponseEntity<List<ProductListDTO>> entry = null;

		try {
			entry = new ResponseEntity<List<ProductListDTO>>(
					service.getListAll(), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ProductListDTO>>(
					HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end list
	
	@GetMapping(value = "/list/{clarge}/{cmedium}/{csmall}")
	public ResponseEntity<List<ProductListDTO>> list(
			Criteria cri, @PathVariable("clarge") String clarge, @PathVariable("cmedium") String cmedium, @PathVariable("csmall") String csmall) {
		ResponseEntity<List<ProductListDTO>> entry = null;

		try {
			entry = new ResponseEntity<List<ProductListDTO>>(
					service.getList(cri, clarge, cmedium, csmall), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ProductListDTO>>(
					HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end list



	
} // end class
