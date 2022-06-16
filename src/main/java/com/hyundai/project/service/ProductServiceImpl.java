package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.ProductDAO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception {
		return productDAO.getProductDetail(pid, ccolorcode);
	}
	
	@Override 
	public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception {
		return productDAO.getProductColorDetail(pid, ccolorcode);
	}

}
