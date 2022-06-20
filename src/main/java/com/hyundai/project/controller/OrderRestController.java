package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;
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

} // end class