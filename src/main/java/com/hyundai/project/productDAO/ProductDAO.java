package com.hyundai.project.productDAO;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.ProductBackDTO;
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
	
	// yoolim start
	public void delProduct(String pid);
	
	public void delColor(String pid);
	
	public void delStock(String pid);
	
	public List<ProductDTO> productSearch(String pname);
	// yoolim end

	// 메인페이지에 보여지는 신상품 및 베스트
	public void updateMain(String pid, int pstatus);
	
	public List<ProductBackDTO> backProductList(String pid);
}
