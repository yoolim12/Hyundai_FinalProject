package com.hyundai.project.service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartUpdateDTO;
import com.hyundai.project.dto.QnaDTO;
import com.hyundai.project.memberDAO.CartDAO;
import com.hyundai.project.memberDAO.QnaDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDAO qnaDAO;

//	@Override
//	public List<CartDTO> getCart(String memail) throws Exception {
//		return cartDAO.getCart(memail);
//	}

	@Override
	public void insertQna(QnaDTO qna) throws Exception {
		qnaDAO.insertQna(qna);
	}
	
//	@Override
//	public void deleteCart(String memail, CartDTO cart) throws Exception {
//		log.info("================================================");
//		log.info(memail);
//		log.info(cart);
//		cartDAO.deleteCart(memail, cart);
//		log.info("delete 완.");
//	}
//
//	@Override
//	public void deleteAllCart(String memail) throws Exception {
//		cartDAO.deleteAllCart(memail);
//	}
//
//	@Override
//	public void updateCart(String memail, CartUpdateDTO cart) throws Exception {
//		cartDAO.updateCart(memail, cart);
//	}
//
//	@Override
//	public int getCartTotal(String memail) throws Exception {
//		return cartDAO.getCartTotal(memail);
//	}

}
