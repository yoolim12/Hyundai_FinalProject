package com.hyundai.project.memberDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.CartDTO;

@Mapper
public interface CartDAO {

	public List<CartDTO> getCart(String memail) throws Exception;

	public void insertCart(String memail, CartDTO cart) throws Exception;

	public void deleteCart(String memail, CartDTO cart) throws Exception;
	
	public void deleteAllCart(String memail) throws Exception;
	
	public void updateCart(String memail, CartDTO cart, String newcolorcode, String newsize, int newqty) throws Exception;
	
}
