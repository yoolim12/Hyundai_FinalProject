package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.project.dto.ProductBackDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.productDAO.ProductDAO;

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
        log.info("produtDAO");
        return productDAO.getProductColorDetail(pid, ccolorcode);
    }

    @Override
    public List<ProductSizeDTO> getProductSize(String pid, String ccolorcode) throws Exception {
        return productDAO.getProductSize(pid, ccolorcode);
    }
    
    @Transactional(value = "productTxManager")
    @Override
    public void delProduct(String pid) {
    	productDAO.delStock(pid);
    	log.info("stock 테이블 삭제");
    	productDAO.delColor(pid);
    	log.info("color 테이블 삭제");
        productDAO.delProduct(pid);
        log.info("product 테이블 삭제");
    }
    
    @Override
    public List<ProductBackDTO> productSearch(String pname) {
        return productDAO.productSearch(pname);
    }

    @Override
    public void updateMain(String pid, int pstatus) throws Exception {
        productDAO.updateMain(pid, pstatus);
    }

	@Override
	public List<ProductBackDTO> backProductList(String pid) throws Exception {
		return productDAO.backProductList(pid);
	}
}