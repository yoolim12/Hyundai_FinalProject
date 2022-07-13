package com.hyundai.project.dto;

import lombok.Data;

/* 작성자 : 문혁
 * 메인 페이지에 보여줄 신상품/베스트 상품 정보를 저장하는 객체
 */
@Data
public class MainListVO {
    private String productCode;
    private String T01imageUrl;
    private String productName;
    private String brandName;
    private String price;

}//end class
