package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/* 작성자 : 문혁
 * 메인 페이지에 출력할 매거진 정보를 저장하는 데이터 클래스
 */
@Data
@AllArgsConstructor
public class MainMagazineListVO {

	private String mobileSubTitle;
	private String desktopSubTitle;
	private String mobileTitle;
	private String imageUrl;
	private String pk;
	private String category;
	private String desktopTitle;
	
}//end class
