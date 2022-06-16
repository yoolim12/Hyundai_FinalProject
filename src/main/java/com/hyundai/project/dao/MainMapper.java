package com.hyundai.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.MainListVO;
import com.hyundai.project.dto.MainMagazineListVO;
/* 작성자 : 문혁
 * 메인 페이지 상품 목록 및 매거진 조회에 관련된 SQL을 호출하는 인터페이스
 */
@Mapper
public interface MainMapper {
	
	public List<MainListVO> getProductList(String pstatus, String category);
	
	public List<MainMagazineListVO> getMagazineList();


}//end inter
