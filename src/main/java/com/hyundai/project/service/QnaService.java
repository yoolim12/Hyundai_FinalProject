package com.hyundai.project.service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartUpdateDTO;
import com.hyundai.project.dto.QnaDTO;

import java.util.List;

public interface QnaService {
	
	//public List<CartDTO> getCart(String memail) throws Exception;
	
	public void insertQna(QnaDTO qna) throws Exception;
	
	//public void deleteCart(String memail, String pid, String ccolorcode, String ssize) throws Exception;
//	public void deleteCart(String memail, CartDTO cart) throws Exception;
//
//	public void deleteAllCart(String memail) throws Exception;
//
//	public void updateCart(String memail, CartUpdateDTO cart) throws Exception;

//	public int getCartTotal(String memail) throws Exception;
	
}
