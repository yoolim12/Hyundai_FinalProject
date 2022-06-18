package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.memberDAO.CartDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;

	@Override
	public List<CartDTO> getCart(String memail) throws Exception {
		return cartDAO.getCart(memail);
	}

	@Override
	public void insertCart(String memail) throws Exception {
		cartDAO.insertCart(memail);
	}

	@Override
	public void deleteCart(String memail) throws Exception {
		cartDAO.deleteCart(memail);
	}

}
