package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MainListVO;
import com.hyundai.project.dto.MainMagazineListVO;
import com.hyundai.project.productDAO.MainDAO;

import lombok.extern.log4j.Log4j2;

/* 작성자 :  문혁
 * 메인 페이지에 관련된 DB 호출을 관리하는 클래스
 */
@Service
@Log4j2
public class MainServiceImpl implements MainService {

    @Autowired
    private MainDAO mapper;

    // 메인 페이지 상품 리스트 조회
    @Override
    public List<MainListVO> getProductList(int pstatus, String category) throws Exception {
        log.info("get Product List");
        return mapper.getProductList(pstatus, category);
    }

    // 메인 페이지 매거진 리스트 조회
    @Override
    public List<MainMagazineListVO> getMagazineList() throws Exception {
        log.info("get Magazine List");
        return mapper.getMagazineList();
    }

}//end class
