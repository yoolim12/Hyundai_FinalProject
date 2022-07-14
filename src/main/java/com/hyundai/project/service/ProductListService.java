package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.ProductListDTO;


public interface ProductListService {
    public List<ProductListDTO> getList(Criteria cri) throws Exception;

    public List<ProductListDTO> getListAll() throws Exception;

    public List<ProductListDTO> getListWithPaging(Criteria cri, String clarge, String cmedium, String csmall) throws Exception;

    public int getTotal(String clarge, String cmedium, String csmall) throws Exception;
}
