package com.hyundai.project.memberDAO;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;

@Mapper
public interface OrderDAO {

	public void insertOrderList(OrderListDTO olist) throws Exception;

	public void insertOrderItem(OrderItemDTO oitem) throws Exception;

	public int getOid() throws Exception;

}
