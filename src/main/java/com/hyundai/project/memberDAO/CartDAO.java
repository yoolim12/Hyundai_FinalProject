package com.hyundai.project.memberDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.CartDTO;

@Mapper
public interface CartDAO {

	public List<CartDTO> getCart(String memail) throws Exception;

	public void insertCart(String memail) throws Exception;

	public void deleteCart(String memail) throws Exception;
	
}
