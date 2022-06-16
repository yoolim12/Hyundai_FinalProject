package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductSizeDTO;

public interface ProductService {
	
	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;
	
	public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception;
	
	public List<ProductSizeDTO> getProductSize(String pid, String ccolorcode) throws Exception;
	
}
