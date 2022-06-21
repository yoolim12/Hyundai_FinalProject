package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;
import com.hyundai.project.dto.OrderResDTO;
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
		try {
			orderDAO.insertOrderList(olist);
			log.info("OrderList 입력 성공");
			int oid = olist.getOid();
			int oitem_sz = olist.getOitem().size();
			for (int i = 0; i < oitem_sz; i++) {
				olist.getOitem().get(i).setOid(oid);
				orderDAO.insertOrderItem(olist.getOitem().get(i));
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
		log.info("OrderItem 입력 성공");
	}

	@Override
	public void insertOrderItem(OrderItemDTO oitem) throws Exception {
		orderDAO.insertOrderItem(oitem);
	}

	@Override
	public List<OrderResDTO> getOrder(String memail) throws Exception {
//		List<OrderResDTO> ol = orderDAO.getOrder(memail);
//		Map<Integer, List<OrderProductDTO>> m = new HashMap<Integer, List<OrderProductDTO>>();
//		for (int i = 0; i < ol.size(); i++) {
//			OrderProductDTO op = new OrderProductDTO();
//			op.setPid(ol.get(i).getPid());
//			op.setPname(ol.get(i).getPname());
//			op.setBname(ol.get(i).getBname());
//			op.setCcolorcode(ol.get(i).getCcolorcode());
//			op.setSsize(ol.get(i).getSsize());
//			op.setQty(ol.get(i).getQty());
//			op.setCimage1(ol.get(i).getCimage1());
//			if (m.containsKey(ol.get(i).getOid())) {
//				m.get(ol.get(i).getOid()).add(op);
//			} else {
//				List<OrderProductDTO> temp = new ArrayList<OrderProductDTO>();
//				temp.add(op);
//				m.put(ol.get(i).getOid(), temp);
//			}
//		}
//		Object[] mapkey = m.keySet().toArray();
//		Arrays.sort(mapkey);
//		log.info(m);
//		for (Integer oid : m.keySet()) {
//			List<OrderProductDTO> temp = m.get(oid);
//			log.info(oid);
//			log.info(temp);
//		}
//		Object[] keys = m.keySet().toArray();
//
//		log.info(keys[1]);
//		List<OrderArrResDTO> or = new ArrayList<OrderArrResDTO>();
//		m.get(keys);
//		for(int i=0; i<m.size(); i++) {
//			OrderArrResDTO temp = new OrderArrResDTO();
//			
//			temp.setOid(Integer.parseInt((String) keys[i]));
//			log.info(temp.getOid());
//			temp.setMemail(ol.get(i).getMemail());
//			temp.setOzipcode(ol.get(i).getOzipcode());
//			temp.setOaddress1(ol.get(i).getOaddress1());
//			temp.setOaddress2(ol.get(i).getOaddress2());
//			temp.setOdate(ol.get(i).getOdate());
//			temp.setOreceiver(ol.get(i).getOreceiver());
//			temp.setOtel(ol.get(i).getOtel());
//			temp.setOusedmileage(ol.get(i).getOid());
//			temp.setOid(ol.get(i).getOid());
//			temp.setOid(ol.get(i).getOid());

		// }
		return orderDAO.getOrder(memail);
  }
  
	public void deleteOrder(int oid) throws Exception {
		orderDAO.deleteOrderList(oid);
		orderDAO.deleteOrderItem(oid);
	}

}