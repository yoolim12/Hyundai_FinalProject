package com.hyundai.project.productDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductSizeDTO;

@Mapper
public interface ProductDAO {
	
 	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;
 	
 	public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception;
 	
 	public List<ProductSizeDTO> getProductSize(String pid, String ccolorcode) throws Exception;
 	
}
