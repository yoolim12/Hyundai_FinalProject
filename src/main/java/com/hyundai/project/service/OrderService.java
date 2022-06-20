package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;

public interface OrderService {
	
	public void insertOrder(OrderListDTO olist) throws Exception;
	
	public void insertOrderItem(OrderItemDTO oitem) throws Exception;
	
}
