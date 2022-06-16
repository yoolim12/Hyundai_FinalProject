package com.hyundai.project.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.ProductDTO;

@Mapper
public interface ProductDAO {
	
 	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;
 	
}
