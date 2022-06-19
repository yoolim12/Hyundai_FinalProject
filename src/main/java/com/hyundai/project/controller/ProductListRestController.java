package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductListDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.service.ProductListService;
import com.hyundai.project.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/product/*")
@RestController
public class ProductListRestController {
	@Autowired
	private ProductListService service;
	
	@Autowired
	private ProductService pservice;
	
	@GetMapping(value = "/productList")
	public ResponseEntity<List<ProductListDTO>> getList() {
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
	public ResponseEntity<List<ProductListDTO>> getProductlist(
			Criteria cri, @PathVariable("clarge") String clarge, @PathVariable("cmedium") String cmedium, @PathVariable("csmall") String csmall) {
		ResponseEntity<List<ProductListDTO>> entry = null;

		try {
			System.out.println(cri);
			entry = new ResponseEntity<List<ProductListDTO>>(
					service.getListWithPaging(cri, clarge, cmedium, csmall), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ProductListDTO>>(
					HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end list
	
	@GetMapping(value = "/color/{pid}/{ccolorcode}")
	public ResponseEntity<List<ProductColorDTO>> getProductColorDetail(@PathVariable("pid") String pid, @PathVariable("ccolorcode") String ccolorcode) {
		ResponseEntity<List<ProductColorDTO>> entry = null;
		try {
			entry = new ResponseEntity<List<ProductColorDTO>>(pservice.getProductColorDetail(pid, ccolorcode), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ProductColorDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}
	
	@GetMapping(value = "/size/{pid}/{ccolorcode}")
	public ResponseEntity<List<ProductSizeDTO>> getProductSize(@PathVariable("pid") String pid, @PathVariable("ccolorcode") String ccolorcode) {
		ResponseEntity<List<ProductSizeDTO>> entry = null;
		try {
			entry = new ResponseEntity<List<ProductSizeDTO>>(pservice.getProductSize(pid, ccolorcode), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ProductSizeDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}
} // end class