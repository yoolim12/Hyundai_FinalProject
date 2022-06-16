package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/* 작성자 : 문혁
 * 메인페이지에 출력할 카테고리 정보를 저장하는 객체
 */
@Data
@AllArgsConstructor
public class MainCategoryListVO {
	// displayTypeYn은 메인에 보여줄 카테고리인지 체크
	// displayType이 true이면 신상품, false이면 베스트

	private Boolean displayTypeYn;
	private Boolean displayType;
	private String categoryName;
	private String categoryCode;
	
}//end class
