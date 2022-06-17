package com.hyundai.project.productDAO;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductListDTO;
import com.hyundai.project.dto.ProductSizeDTO;

@Mapper
public interface ProductDAO {

	public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;

	public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception;

	public List<ProductSizeDTO> getProductSize(String pid, String ccolorcode) throws Exception;

	public List<ProductListDTO> getList(Criteria cri) throws SQLException;

	public List<ProductListDTO> getListAll() throws SQLException;

	public List<ProductListDTO> getListWithPaging(Criteria cri, String clarge, String cmedium, String csmall)
			throws SQLException;

	public int getTotal(String clarge, String cmedium, String csmall) throws SQLException;
}
