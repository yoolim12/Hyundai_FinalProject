package com.hyundai.project.memberDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;
import com.hyundai.project.dto.OrderResDTO;

@Mapper
public interface OrderDAO {

	public void insertOrderList(OrderListDTO olist) throws Exception;

	public void insertOrderItem(OrderItemDTO oitem) throws Exception;
	
	public List<OrderResDTO> getOrder(String memail) throws Exception;

	public int getOid() throws Exception;
	
	public void deleteOrderList(int oid) throws Exception;

	public void deleteOrderItem(int oid) throws Exception;

	public void updateOrderList(int oid) throws Exception;

	public OrderListDTO getOrderDetail(int oid) throws Exception;
	// 총 회원 수
	public long getTotalMember() throws Exception;
	// 상품 총 판매량
	public long getTotalAmount() throws Exception;
	// 이번 주의 주문 건수
	public long getOrderAmountOfWeek() throws Exception;
	// 총 매출
	public long getTotalRevenue() throws Exception;
}
