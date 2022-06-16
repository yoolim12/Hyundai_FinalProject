package com.hyundai.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.ProductListDTO;

@Mapper
@Configuration
public interface ProductListDAO {

	public List<ProductListDTO> getList(Criteria cri) throws SQLException;
	public List<ProductListDTO> getList(String pid) throws SQLException;
	public List<ProductListDTO> getListAll() throws SQLException;
	public List<ProductListDTO> getListWithPaging(Criteria cri, String clarge, String cmedium, String csmall) throws SQLException;
}