package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.ProductListDTO;


public interface ProductListService {
	public List<ProductListDTO> getList(Criteria cri) throws Exception;
	public List<ProductListDTO> getListAll() throws Exception;	
	public List<ProductListDTO> getList(Criteria cri, String clarge, String cmedium, String csmall) throws Exception;
}
