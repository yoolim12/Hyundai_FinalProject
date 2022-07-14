package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.Criteria;
import com.hyundai.project.dto.ProductListDTO;
import com.hyundai.project.productDAO.ProductDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductListServiceImpl implements ProductListService {
    @Autowired
    private ProductDAO mapper;

    @Override
    public List<ProductListDTO> getList(Criteria cri) throws Exception {
        return mapper.getList(cri);
    }

    @Override
    public List<ProductListDTO> getListAll() throws Exception {
        return mapper.getListAll();
    }

    @Override
    public List<ProductListDTO> getListWithPaging(Criteria cri, String clarge, String cmedium, String csmall) throws Exception {
        log.info("get List with criteria: " + cri);
        return mapper.getListWithPaging(cri, clarge, cmedium, csmall);
    }

    @Override
    public int getTotal(String clarge, String cmedium, String csmall) throws Exception {
        log.info("get Total Product");
        return mapper.getTotal(clarge, cmedium, csmall);
    }

}
