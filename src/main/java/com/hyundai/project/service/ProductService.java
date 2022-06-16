package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;

public interface ProductService {
	
	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;
	
	public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception;
	
}
