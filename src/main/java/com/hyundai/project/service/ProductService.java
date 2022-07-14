package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.ProductBackDTO;
import com.hyundai.project.dto.ProductCategoryDTO;
import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductInsertDTO;
import com.hyundai.project.dto.ProductSizeDTO;

public interface ProductService {

    public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception;

    public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception;

    public List<ProductSizeDTO> getProductSize(String pid, String ccolorcode) throws Exception;

    // yoolim start
    public void delProduct(String pid);

    public List<ProductBackDTO> productSearch(String pname);
    // yoolim end

    public void updateMain(String pid, int pstatus) throws Exception;

    public void updateProduct(ProductDTO product) throws Exception;

    public void insertProduct(ProductInsertDTO product) throws Exception;

    public List<ProductCategoryDTO> getCategory() throws Exception;

    public List<ProductBackDTO> backProductList(String pid) throws Exception;

    public int productModify(ProductBackDTO productBackDTO);
}
