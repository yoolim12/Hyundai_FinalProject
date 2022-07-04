package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.memberDAO.CartDAO;
import com.hyundai.project.memberDAO.MemberDAO;
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
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private MemberDAO memberDAO;

    @Transactional(value = "memberTxManager")
    @Override
    public void insertOrder(OrderListDTO olist) throws Exception {
        try {
            orderDAO.insertOrderList(olist);
            log.info("OrderList 입력 성공");
            int oid = olist.getOid();
            int oitem_sz = olist.getOitem().size();
            String email = olist.getMemail();
            log.info("oite_sz : " + oitem_sz);
            for (int i = 0; i < oitem_sz; i++) {
                olist.getOitem().get(i).setOid(oid);
                orderDAO.insertOrderItem(olist.getOitem().get(i));
                log.info("OrderItem 입력 성공");

                CartDTO cart = new CartDTO();
                cart.setBname("bname");
                cart.setPname("pname");
                cart.setPprice("11111");
                cart.setCimage1("cimage1");
                cart.setPid(olist.getOitem().get(i).getPid());
                cart.setCcolorcode(olist.getOitem().get(i).getCcolorcode());
                cart.setSsize(olist.getOitem().get(i).getSsize());
                log.info("OList getMemail" + olist.getMemail());
                log.info("CART : " + cart);

                cartDAO.deleteCart(email, cart);
                log.info("Cart 삭제 성공");
            }
            log.info("for문 종료");
            // 포인트 차감
            log.info("memail : " + olist.getMemail());
            log.info("point : " + olist.getOusedpoint());
            memberDAO.pointApply(olist.getMemail(), olist.getOusedpoint());
            log.info("포인트 차감");
            int savingpoint = (int) Math.ceil((double)(olist.getOprice()-olist.getOusedpoint())*0.05);
            if(olist.getOusedpoint() != olist.getOprice()) {
                log.info("포인트 적립 : " + savingpoint);
                memberDAO.pointSaving(olist.getMemail(), savingpoint);
            }
        } catch (Exception e) {
            log.info("ERROR:" + e.getMessage());
            throw e;
        }

    }

    @Override
    public void insertOrderItem(OrderItemDTO oitem) throws Exception {
        orderDAO.insertOrderItem(oitem);
    }

    @Override
    public List<OrderResDTO> getOrder(String memail) throws Exception {
    	log.info("마이페이지 주문 조회");
        return orderDAO.getOrder(memail);
    }


    public void deleteOrder(int oid) throws Exception {
        orderDAO.deleteOrderList(oid);
        orderDAO.deleteOrderItem(oid);
    }

    @Transactional(value = "memberTxManager")
    @Override
    public void updateOrderList(int oid) throws Exception {
    	orderDAO.updateOrderList(oid);
        OrderListDTO order = orderDAO.getOrderDetail(oid);
        int savingpoint = (int) Math.ceil((double)(order.getOprice()-order.getOusedpoint())*0.05);
        memberDAO.pointApply(order.getMemail(), savingpoint);
        memberDAO.pointSaving(order.getMemail(), order.getOusedpoint());
        log.info(savingpoint);
    }
}
