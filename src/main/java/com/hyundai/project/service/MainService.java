package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.MainListVO;
import com.hyundai.project.dto.MainMagazineListVO;
/* 작성자 :  문혁
 * 메인 페이지에 관련된 DB 호출을 관리하는 인터페이스
 */
public interface MainService {
	
	// 상품 리스트 조회. ref 값은 new = 신상품, best = 베스트
	public List<MainListVO> getProductList(String pstatus, String category) throws Exception;
	
	// 매거진 리스트 조회
	public List<MainMagazineListVO> getMagazineList() throws Exception;

	
}//end interface
