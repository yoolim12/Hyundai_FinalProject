package com.hyundai.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;

@Mapper
public interface ProductDAO {
	
 	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;
 	
 	public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception;
 	
}
