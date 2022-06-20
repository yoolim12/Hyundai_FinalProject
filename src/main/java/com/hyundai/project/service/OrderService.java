package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;
import com.hyundai.project.dto.OrderResDTO;

public interface OrderService {
	
	public void insertOrder(OrderListDTO olist) throws Exception;
	
	public void insertOrderItem(OrderItemDTO oitem) throws Exception;
	
	public List<OrderResDTO> getOrder(String memail) throws Exception;
	
}
