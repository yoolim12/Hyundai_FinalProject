package com.hyundai.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;
import com.hyundai.project.memberDAO.OrderDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Transactional(value = "transactionManager")
	@Override
	public void insertOrder(OrderListDTO olist) throws Exception {
		orderDAO.insertOrderList(olist);
		log.info("OrderList 입력 성공");
		int oid = olist.getOid();
		int oitem_sz = olist.getOitem().size();
		log.info("item insert 전");
		for (int i = 0; i < oitem_sz; i++) {
			olist.getOitem().get(i).setOid(oid);
			orderDAO.insertOrderItem(olist.getOitem().get(i));
		}
		log.info("item insert 성공");
	}

	@Override
	public void insertOrderItem(OrderItemDTO oitem) throws Exception {

		orderDAO.insertOrderItem(oitem);
	}

}
