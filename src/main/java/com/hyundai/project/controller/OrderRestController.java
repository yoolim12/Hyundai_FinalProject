package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;
import com.hyundai.project.dto.OrderResDTO;
import com.hyundai.project.service.OrderService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/order")
@RestController
public class OrderRestController {
	@Autowired
	private OrderService service;

	@PostMapping
	public String insertOrderList(@RequestBody OrderListDTO olist) {
		log.info(olist);
		try {
			service.insertOrder(olist);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
		return "ORDERLIST INSERT success";
	}// end list
	
	@PostMapping(value = "/item")
	public String insertOrderItem(@RequestBody OrderItemDTO oitem) {

		try {
			service.insertOrderItem(oitem);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
		return "ORDERITEM INSERT success";
	}// end list
	
	@GetMapping(value = "/{memail}")
	public ResponseEntity<List<OrderResDTO>> getOrder(@PathVariable("memail") String memail) {
		ResponseEntity<List<OrderResDTO>> entry = null;

		try {
			entry = new ResponseEntity<List<OrderResDTO>>(service.getOrder(memail), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<OrderResDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end list

} // end class