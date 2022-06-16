package com.hyundai.project.service;

import com.hyundai.project.dto.ProductDTO;

public interface ProductService {
	
	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;
	
}
