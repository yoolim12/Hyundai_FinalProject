package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.CartDTO;

public interface CartService {
	
	public List<CartDTO> getCart(String memail) throws Exception;
	
	public void insertCart(String memail, CartDTO cart) throws Exception;
	
	//public void deleteCart(String memail, String pid, String ccolorcode, String ssize) throws Exception;
	public void deleteCart(String memail, CartDTO cart) throws Exception;
	
	public void deleteAllCart(String memail) throws Exception;
	
	public void updateCart(String memail, CartDTO cart, String newcolorcode, String newsize, int newqty) throws Exception;
	
}
